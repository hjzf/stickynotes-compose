package logic

import org.apache.lucene.document.*
import org.apache.lucene.index.*
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.Sort
import org.apache.lucene.search.SortField
import org.apache.lucene.store.MMapDirectory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.wltea.analyzer.lucene.IKAnalyzer
import tool.formatTimestamp
import java.nio.file.Path
import java.util.*

object Lucene {

    private val log: Logger = LoggerFactory.getLogger("Lucene")

    fun query(q: String, notes: Collection<Note>, indexPath: Path): List<NoteCard> {
        try {
            if (q.isEmpty()) {
                return notes.sortedByDescending { it.updateTime }.map {
                    NoteCard(
                        key = it.id,
                        noteId = it.id,
                        position1 = it.position1,
                        position2 = it.position2,
                        description = it.description,
                        visible = it.visible,
                        style = it.style,
                        updateTime = formatTimestamp(it.updateTime),
                    )
                }
            }
            MMapDirectory.open(indexPath).use { directory ->
                IKAnalyzer().use { analyzer ->
                    DirectoryReader.open(directory).use { directoryReader ->
                        val indexSearcher = IndexSearcher(directoryReader)
                        val queryParser = QueryParser(INDEX_FIELD_NAME, analyzer)
                        val lowercaseQueryString = q.lowercase(Locale.getDefault())
                        val query = queryParser.parse("\"${QueryParser.escape(lowercaseQueryString)}\"")
                        val sort = Sort(SortField(UPDATE_TIME, SortField.Type.LONG, true))
                        val topDocs = indexSearcher.search(query, 1024, sort)
                        val scoreDocs = topDocs.scoreDocs
                        if (scoreDocs.isEmpty()) {
                            return emptyList()
                        } else {
                            val noteMap = notes.associateBy { it.id }
                            val noteCards = ArrayList<NoteCard>(scoreDocs.size)
                            val storedFields = indexSearcher.storedFields()
                            for (scoreDoc in scoreDocs) {
                                val document = storedFields.document(scoreDoc.doc)
                                val noteId = document.get(NOTE_ID) ?: continue
                                val note = noteMap[noteId] ?: continue
                                val blockId = document.get(BLOCK_ID) ?: continue
                                val content = document.get(CONTENT) ?: continue
                                val index = content.indexOf(q)
                                val description = if (index >= 8) {
                                    content.substring(index - 8)
                                } else {
                                    content
                                }
                                noteCards.add(
                                    NoteCard(
                                        key = "${noteId}_${blockId}",
                                        noteId = noteId,
                                        position1 = document.get(ORDER)?.toIntOrNull() ?: 0,
                                        position2 = 0,
                                        description = description,
                                        visible = note.visible,
                                        style = note.style,
                                        updateTime = formatTimestamp(note.updateTime)
                                    )
                                )
                            }
                            return noteCards
                        }
                    }
                }
            }
        } catch (_: IndexNotFoundException) {
            MMapDirectory.open(indexPath).use { directory ->
                IKAnalyzer().use { analyzer ->
                    val indexWriterConfig = IndexWriterConfig(analyzer)
                    indexWriterConfig.setCommitOnClose(true)
                    IndexWriter(directory, indexWriterConfig).use { indexWriter ->
                        val document = Document()
                        document.add(StringField(KEY, INIT, Field.Store.YES))
                        indexWriter.addDocument(document)
                    }
                }
            }
            return emptyList()
        } catch (e: Exception) {
            log.error("Failed to query", e)
            return emptyList()
        }
    }

    fun updateIndexForNote(noteId: String, blocks: List<Block>, updateTime: Long, indexPath: Path) {
        try {
            MMapDirectory.open(indexPath).use { directory ->
                IKAnalyzer().use { analyzer ->
                    val indexWriterConfig = IndexWriterConfig(analyzer)
                    indexWriterConfig.setCommitOnClose(true)
                    IndexWriter(directory, indexWriterConfig).use { indexWriter ->
                        indexWriter.deleteDocuments(Term(NOTE_ID, noteId))
                        val documents = ArrayList<Document>(blocks.size)
                        blocks.withIndex().forEach { (index, block) ->
                            when (block.type) {
                                BlockType.TEXT, BlockType.BOLD, BlockType.UNDERLINE, BlockType.ITALIC, BlockType.LINE_THROUGH -> {
                                    val document = createDocument(noteId, updateTime, block, index.toString())
                                    if (document != null) {
                                        documents.add(document)
                                    }
                                }

                                BlockType.IMAGE -> {}
                            }
                        }
                        indexWriter.addDocuments(documents)
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update index", e)
        }
    }

    fun updateIndexForNotes(list: Collection<Pair<Note, List<Block>>>, indexPath: Path) {
        try {
            MMapDirectory.open(indexPath).use { directory ->
                IKAnalyzer().use { analyzer ->
                    val indexWriterConfig = IndexWriterConfig(analyzer)
                    indexWriterConfig.setCommitOnClose(true)
                    IndexWriter(directory, indexWriterConfig).use { indexWriter ->
                        val documents = ArrayList<Document>()
                        list.forEach { (note, blocks) ->
                            indexWriter.deleteDocuments(Term(NOTE_ID, note.id))
                            blocks.withIndex().forEach { (index, block) ->
                                when (block.type) {
                                    BlockType.TEXT, BlockType.BOLD, BlockType.UNDERLINE, BlockType.ITALIC, BlockType.LINE_THROUGH -> {
                                        val document = createDocument(note.id, note.updateTime, block, index.toString())
                                        if (document != null) {
                                            documents.add(document)
                                        }
                                    }

                                    BlockType.IMAGE -> {}
                                }
                            }
                        }
                        indexWriter.addDocuments(documents)
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update index for notes", e)
        }
    }

    private fun createDocument(noteId: String, updateTime: Long, block: Block, order: String): Document? {
        try {
            val indexFieldValue = StringBuilder(block.content.lowercase(Locale.getDefault())).append(' ')
                .append(formatTimestamp(updateTime, "yyyyMMdd")).append(' ')
                .append(formatTimestamp(updateTime, "yyyy/MM/dd")).append(' ')
                .append(formatTimestamp(updateTime, "yyyy-MM-dd")).append(' ')
                .append(formatTimestamp(updateTime, "yyyy_MM_dd")).toString()
            val document = Document()
            document.add(TextField(INDEX_FIELD_NAME, indexFieldValue, Field.Store.NO))
            document.add(StringField(NOTE_ID, noteId, Field.Store.YES))
            document.add(StringField(BLOCK_ID, block.id.toString(), Field.Store.YES))
            document.add(StoredField(CONTENT, block.content))
            document.add(StoredField(ORDER, order))
            document.add(NumericDocValuesField(UPDATE_TIME, updateTime))
            return document
        } catch (e: Exception) {
            log.error("Failed to create document", e)
            return null
        }
    }

    private const val INDEX_FIELD_NAME = "index_field"

    private const val NOTE_ID = "note_id"

    private const val BLOCK_ID = "block_id"

    private const val CONTENT = "content"

    private const val ORDER = "order"

    private const val UPDATE_TIME = "update_time"

    private const val KEY = "key"

    private const val INIT = "init"

}
