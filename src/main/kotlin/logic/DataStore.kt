package logic

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.flow.MutableSharedFlow
import org.jetbrains.skia.Image.Companion.makeFromEncoded
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tool.*
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.*
import kotlin.collections.ArrayList
import kotlin.io.path.*

private val log: Logger = LoggerFactory.getLogger("DataStore")

class LRUCache(private val maxElements: Int) : LinkedHashMap<String, ImageBitmap?>(maxElements, 0.75f, true) {
    override fun removeEldestEntry(eldest: Map.Entry<String, ImageBitmap?>): Boolean = size > maxElements
}

object DataStore {

    val noteContentVersionFlow = MutableSharedFlow<Long>(replay = 1)

    val windowStateVersionFlow = MutableSharedFlow<Long>(replay = 1)

    val dataPathVersionFlow = MutableSharedFlow<Long>(replay = 1)

    private var _profileState = ProfileState()

    private var _imageCache = LRUCache(16)

    private const val NOTES_TXT = "notes.txt"

    fun loadImage(key: String): ImageBitmap? {
        val image = _imageCache[key]
        if (image != null) {
            return image
        }
        var composeImageBitmap: ImageBitmap? = null
        try {
            val file = File(key)
            val absoluteFile = if (file.isAbsolute) file else File(_profileState.dataPath, key)
            if (absoluteFile.exists() && absoluteFile.isFile && absoluteFile.canRead()) {
                try {
                    val skiaImage = makeFromEncoded(absoluteFile.readBytes())
                    composeImageBitmap = skiaImage.toComposeImageBitmap()
                    _imageCache[key] = composeImageBitmap
                } catch (e: Exception) {
                    log.error("makeFromEncoded error", e)
                }
            }
        } catch (e: Exception) {
            log.error("loadImage error", e)
        }
        return composeImageBitmap
    }

    fun loadProfileState(profileFilePath: Path, defaultDataPath: Path): ProfileState {
        try {
            val defaultProfileState = ProfileState(dataPath = defaultDataPath.toString())
            _profileState = profileFilePath.readAsProfileState(charset = Charsets.UTF_8, defaultProfileState)
            _imageCache = LRUCache(_profileState.imageCache)
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to load profile state", e)
            return ProfileState(dataPath = defaultDataPath.toString())
        }
    }

    private fun saveProfileState(profileState: ProfileState, profileFilePath: Path) {
        try {
            profileFilePath.writeText(profileState.toText(), charset = Charsets.UTF_8)
        } catch (e: Exception) {
            log.error("Failed to save profile state", e)
        }
    }

    fun updateFontSize(fontSize: Int, profileFilePath: Path): ProfileState {
        try {
            if (fontSize != _profileState.fontSize) {
                _profileState = _profileState.copy(fontSize = fontSize)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update font size", e)
            return _profileState
        }
    }

    fun updateFontWeight(fontWeight: Int, profileFilePath: Path): ProfileState {
        try {
            if (fontWeight != _profileState.fontWeight) {
                _profileState = _profileState.copy(fontWeight = fontWeight)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update font weight", e)
            return _profileState
        }
    }

    fun updateFontFamily(fontFamily: String, profileFilePath: Path): ProfileState {
        try {
            if (fontFamily != _profileState.fontFamily) {
                _profileState = _profileState.copy(fontFamily = fontFamily)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update font family", e)
            return _profileState
        }
    }

    fun updateBackgroundAlpha(backgroundAlpha: Int, profileFilePath: Path): ProfileState {
        try {
            if (backgroundAlpha != _profileState.backgroundAlpha) {
                _profileState = _profileState.copy(backgroundAlpha = backgroundAlpha)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update background alpha", e)
            return _profileState
        }
    }

    suspend fun updateDataPath(dataPath: String, profileFilePath: Path): ProfileState {
        try {
            if (dataPath != _profileState.dataPath) {
                _profileState = _profileState.copy(dataPath = dataPath)
                saveProfileState(_profileState, profileFilePath)
                dataPathVersionFlow.emit(System.currentTimeMillis())
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update data path", e)
            return _profileState
        }
    }

    fun updateMarkdownPath(markdownPath: String, profileFilePath: Path): ProfileState {
        try {
            if (markdownPath != _profileState.markdownPath) {
                _profileState = _profileState.copy(markdownPath = markdownPath)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update markdown path", e)
            return _profileState
        }
    }

    fun updateImageCache(imageCache: Int, profileFilePath: Path): ProfileState {
        try {
            if (imageCache != _profileState.imageCache) {
                _profileState = _profileState.copy(imageCache = imageCache)
                _imageCache = LRUCache(_profileState.imageCache)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update image cache", e)
            return _profileState
        }
    }

    fun updateCopyWhenClick(copyWhenClick: Boolean, profileFilePath: Path): ProfileState {
        try {
            if (copyWhenClick != _profileState.copyWhenClick) {
                _profileState = _profileState.copy(copyWhenClick = copyWhenClick)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update copyWhenClick", e)
            return _profileState
        }
    }

    fun updateColorTheme(colorTheme: String, profileFilePath: Path): ProfileState {
        try {
            if (colorTheme != _profileState.colorTheme) {
                _profileState = _profileState.copy(colorTheme = colorTheme)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update color theme", e)
            return _profileState
        }
    }

    fun updateLanguage(language: String, profileFilePath: Path): ProfileState {
        try {
            if (language != _profileState.language) {
                _profileState = _profileState.copy(language = language)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update language", e)
            return _profileState
        }
    }

    fun updateTooltip(tooltip: Boolean, profileFilePath: Path): ProfileState {
        try {
            if (tooltip != _profileState.tooltip) {
                _profileState = _profileState.copy(tooltip = tooltip)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update tooltip", e)
            return _profileState
        }
    }

    fun updateTransition(transition: Boolean, profileFilePath: Path): ProfileState {
        try {
            if (transition != _profileState.transition) {
                _profileState = _profileState.copy(transition = transition)
                saveProfileState(_profileState, profileFilePath)
            }
            return _profileState
        } catch (e: Exception) {
            log.error("Failed to update transition", e)
            return _profileState
        }
    }

    suspend fun addNewNote(): Note {
        try {
            val notes = loadNotes().toMutableList()
            val timestamp = currentTimeAsTimestamp()
            val newNote = Note(
                id = UUID.randomUUID().toString().replace("-", ""),
                visible = true,
                updateTime = timestamp,
                createTime = timestamp
            )
            notes.add(newNote)
            saveNotes(notes)
            saveBlocks(newNote.id, emptyList(), timestamp)
            windowStateVersionFlow.emit(System.currentTimeMillis())
            return newNote
        } catch (e: Exception) {
            log.error("Failed to add new note", e)
            return Note()
        }
    }

    suspend fun deleteNote(id: String) {
        try {
            val notes = loadNotes().toMutableList()
            val index = notes.indexOfFirst { it.id == id }
            if (index >= 0) {
                notes.removeAt(index)
                saveNotes(notes)
                windowStateVersionFlow.emit(System.currentTimeMillis())
            }
        } catch (e: Exception) {
            log.error("Failed to delete note", e)
        }
    }

    suspend fun updateNoteVisible(id: String, visible: Boolean) {
        try {
            val notes = loadNotes().toMutableList()
            if (id.isNotEmpty()) {
                val index = notes.indexOfFirst { it.id == id }
                if (index >= 0) {
                    if (notes[index].visible != visible) {
                        notes[index] = notes[index].copy(visible = visible)
                        saveNotes(notes)
                        windowStateVersionFlow.emit(System.currentTimeMillis())
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update note visible", e)
        }
    }

    suspend fun updateNoteVisibleAndPosition(id: String, visible: Boolean, position1: Int, position2: Int) {
        try {
            val notes = loadNotes().toMutableList()
            if (id.isNotEmpty()) {
                val index = notes.indexOfFirst { it.id == id }
                if (index >= 0) {
                    if (notes[index].visible != visible) {
                        notes[index] = notes[index].copy(
                            visible = visible,
                            position1 = position1,
                            position2 = position2
                        )
                        saveNotes(notes)
                        windowStateVersionFlow.emit(System.currentTimeMillis())
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update note visible and position", e)
        }
    }

    fun updateNotePosition(id: String, position1: Int, position2: Int, position3: Int) {
        try {
            val notes = loadNotes().toMutableList()
            if (id.isNotEmpty()) {
                val index = notes.indexOfFirst { it.id == id }
                if (index >= 0) {
                    if (notes[index].position1 != position1 || notes[index].position2 != position2 || notes[index].position3 != position3) {
                        notes[index] = notes[index].copy(
                            position1 = position1, position2 = position2, position3 = position3
                        )
                        saveNotes(notes)
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update note position", e)
        }
    }

    suspend fun updateNoteStyle(id: String, style: String) {
        try {
            val notes = loadNotes().toMutableList()
            if (id.isNotEmpty()) {
                val index = notes.indexOfFirst { it.id == id }
                if (index >= 0) {
                    if (notes[index].style != style) {
                        notes[index] = notes[index].copy(style = style)
                        saveNotes(notes)
                        windowStateVersionFlow.emit(System.currentTimeMillis())
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update note style", e)
        }
    }

    fun updateNoteAlwaysOnTop(id: String, alwaysOnTop: Boolean) {
        try {
            val notes = loadNotes().toMutableList()
            if (id.isNotEmpty()) {
                val index = notes.indexOfFirst { it.id == id }
                if (index >= 0) {
                    if (notes[index].alwaysOnTop != alwaysOnTop) {
                        notes[index] = notes[index].copy(alwaysOnTop = alwaysOnTop)
                        saveNotes(notes)
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update note AlwaysOnTop", e)
        }
    }

    fun updateNoteWidthAndHeight(id: String, width: Int, height: Int) {
        try {
            val notes = loadNotes().toMutableList()
            if (id.isNotEmpty()) {
                val index = notes.indexOfFirst { it.id == id }
                if (index >= 0) {
                    if (notes[index].width != width || notes[index].height != height) {
                        notes[index] = notes[index].copy(width = width, height = height)
                        saveNotes(notes)
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update note width and height", e)
        }
    }

    fun updateNoteXAndY(id: String, x: Int, y: Int) {
        try {
            val notes = loadNotes().toMutableList()
            if (id.isNotEmpty()) {
                val index = notes.indexOfFirst { it.id == id }
                if (index >= 0) {
                    if (notes[index].x != x || notes[index].y != y) {
                        notes[index] = notes[index].copy(x = x, y = y)
                        saveNotes(notes)
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update note x and y", e)
        }
    }

    fun updateNoteMode(id: String, mode: Int) {
        try {
            val notes = loadNotes().toMutableList()
            if (id.isNotEmpty()) {
                val index = notes.indexOfFirst { it.id == id }
                if (index >= 0) {
                    if (notes[index].mode != mode) {
                        notes[index] = notes[index].copy(mode = mode)
                        saveNotes(notes)
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update note mode", e)
        }
    }

    suspend fun updateNoteContent(id: String, blocks: List<Block>) {
        try {
            val notes = loadNotes().toMutableList()
            if (id.isNotEmpty()) {
                val index = notes.indexOfFirst { it.id == id }
                if (index >= 0) {
                    val note = notes[index]
                    if (!equals(blocks, loadBlocks(note.id))) {
                        val timestamp = currentTimeAsTimestamp()
                        notes[index] = note.copy(
                            description = calculateDescription(blocks), updateTime = timestamp
                        )
                        saveNotes(notes)
                        saveBlocks(id, blocks, timestamp)
                        noteContentVersionFlow.emit(System.currentTimeMillis())
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Failed to update note content", e)
        }
    }

    private fun calculateDescription(blocks: List<Block>): String {
        try {
            val stringBuilder = StringBuilder()
            for (i in 0..minOf(5, blocks.size - 1)) {
                stringBuilder.append(blocks[i].content)
            }
            return stringBuilder.toString()
        } catch (e: Exception) {
            log.error("Failed to calculate description", e)
            return ""
        }
    }

    fun loadNotes(): List<Note> {
        try {
            return (Path(_profileState.dataPath) / NOTES_TXT).readAsNotes(charset = Charsets.UTF_8)
        } catch (e: Exception) {
            log.error("Failed to load notes", e)
            return emptyList()
        }
    }

    private fun saveNotes(notes: MutableList<Note>) {
        try {
            (Path(_profileState.dataPath) / NOTES_TXT).writeText(notes.toText(), charset = Charsets.UTF_8)
        } catch (e: Exception) {
            log.error("Failed to save notes", e)
        }
    }

    fun loadBlocks(id: String): List<Block> {
        try {
            return (Path(_profileState.dataPath) / "${id}.txt").readAsBlocks(charset = Charsets.UTF_8)
        } catch (e: Exception) {
            log.error("Failed to load blocks for note $id", e)
            return emptyList()
        }
    }

    private fun saveBlocks(noteId: String, blocks: List<Block>, updateTime: Long) {
        try {
            val dataPath = Path(_profileState.dataPath)
            (dataPath / "${noteId}.txt").writeText(blocks.toText(), charset = Charsets.UTF_8)
            Lucene.updateIndexForNote(noteId, blocks, updateTime, dataPath / "index")
        } catch (e: Exception) {
            log.error("Failed to save blocks for note $noteId", e)
        }
    }

    private fun String.escape(): String {
        return this.lineSequence().joinToString("\n") { line ->
            when (line.firstOrNull()) {
                '#', '\\' -> "\\$line"
                else -> line
            }
        }
    }

    fun loadBlocksAsRawText(id: String): String {
        try {
            val blocks = loadBlocks(id)
            if (blocks.isEmpty()) {
                return ""
            }
            if (blocks.size == 1) {
                val singleBlock = blocks[0]
                return if (singleBlock.type == BlockType.TEXT) {
                    singleBlock.content.escape()
                } else {
                    "# ${singleBlock.type.description}\n${singleBlock.content.escape()}"
                }
            }
            val builder = StringBuilder()
            val firstBlock = blocks[0]
            if (firstBlock.type == BlockType.TEXT) {
                builder.append(firstBlock.content.escape()).append('\n')
            } else {
                builder.append("# ").append(firstBlock.type.description).append('\n').append(firstBlock.content.escape()).append('\n')
            }
            for (i in 1..(blocks.size - 2)) {
                val block = blocks[i]
                builder.append("# ").append(block.type.description).append('\n').append(block.content.escape()).append('\n')
            }
            val lastBlock = blocks[blocks.size - 1]
            builder.append("# ").append(lastBlock.type.description).append('\n').append(lastBlock.content.escape())
            return builder.toString()
        } catch (e: Exception) {
            log.error("Failed to load blocks as raw text", e)
            return ""
        }
    }

    suspend fun saveRawTextAsBlocks(id: String, rawText: String) {
        try {
            if (rawText.isEmpty()) {
                updateNoteContent(id, emptyList())
                return
            }
            val lines = rawText.lines()
            if (lines.isEmpty()) {
                updateNoteContent(id, emptyList())
                return
            }
            var blockType: BlockType? = null
            var contentLines: ArrayList<String>? = null
            val firstLine = lines[0]
            if (!firstLine.startsWith("#")) {
                blockType = BlockType.TEXT
                contentLines = ArrayList()
            }
            val blocks = ArrayList<Block>(lines.size)
            var index = 0
            for (line in lines) {
                if (line.startsWith("#")) {
                    if (blockType != null && contentLines != null) {
                        blocks.add(Block(blockType, contentLines.joinToString("\n"), index))
                        index++
                    }
                    val typeName = line.substring(1).trim().lowercase(Locale.getDefault())
                    blockType = when (typeName) {
                        BlockType.TEXT.description -> BlockType.TEXT
                        BlockType.BOLD.description -> BlockType.BOLD
                        BlockType.ITALIC.description -> BlockType.ITALIC
                        BlockType.UNDERLINE.description -> BlockType.UNDERLINE
                        BlockType.LINE_THROUGH.description -> BlockType.LINE_THROUGH
                        BlockType.IMAGE.description -> BlockType.IMAGE
                        else -> BlockType.TEXT
                    }
                    contentLines = ArrayList()
                } else if (line.startsWith("\\#") || line.startsWith("\\\\")) {
                    contentLines?.add(line.substring(1))
                } else {
                    contentLines?.add(line)
                }
            }
            if (blockType != null && contentLines != null) {
                blocks.add(Block(blockType, contentLines.joinToString("\n"), index))
            }
            updateNoteContent(id, blocks)
        } catch (e: Exception) {
            log.error("Failed to save raw text as blocks", e)
        }
    }

    fun updateIndex() {
        try {
            val notes = loadNotes()
            if (notes.isEmpty()) {
                return
            }
            val list = notes.map { it to loadBlocks(it.id) }
            Lucene.updateIndexForNotes(list, Path(_profileState.dataPath) / "index")
        } catch (e: Exception) {
            log.error("Failed to update index", e)
        }
    }

    fun search(target: String): List<NoteCard> {
        try {
            return Lucene.query(target, loadNotes(), Path(_profileState.dataPath) / "index")
        } catch (e: Exception) {
            log.error("Failed to search", e)
            return emptyList()
        }
    }

    fun exportMarkdown(id: String): Boolean {
        try {
            val markdownFilePath = Path(_profileState.markdownPath) / "${APP_NAME}_${id}" / "index.md"
            markdownFilePath.createParentDirectories()
            val images = "images"
            val imagesPath = markdownFilePath.parent / images
            if (!imagesPath.exists()) {
                imagesPath.createDirectory()
            }
            val blocks = loadBlocks(id)
            val markdownTextList = ArrayList<String>(blocks.size)
            for (block in blocks) {
                when (block.type) {
                    BlockType.TEXT -> {
                        markdownTextList.add(block.content)
                    }

                    BlockType.BOLD -> {
                        markdownTextList.add("**${block.content.replace("*", "\\*")}**")
                    }

                    BlockType.ITALIC -> {
                        markdownTextList.add("*${block.content.replace("*", "\\*")}*")
                    }

                    BlockType.UNDERLINE -> {
                        markdownTextList.add("<u>${block.content.escapeHtml4()}</u>")
                    }

                    BlockType.LINE_THROUGH -> {
                        markdownTextList.add("~~${block.content}~~")
                    }

                    BlockType.IMAGE -> {
                        val key = block.content.trim()
                        val path = Path(key)
                        val sourcePath = if (path.isAbsolute) {
                            path
                        } else {
                            Path(_profileState.dataPath) / key
                        }
                        val fileName = sourcePath.fileName.name
                        val targetPath = imagesPath / fileName
                        try {
                            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING)
                        } catch (exception: Exception) {
                            log.error("Failed to copy image", exception)
                        }
                        markdownTextList.add("![${fileName}](./${images}/${fileName})")
                    }
                }
            }
            markdownFilePath.writeText(markdownTextList.joinToString("\n\n"), charset = Charsets.UTF_8)
            return true
        } catch (e: Exception) {
            log.error("Failed to export markdown", e)
            return false
        }
    }

}

private fun equals(block1: Block?, block2: Block?): Boolean {
    if (block1 === block2) {
        return true
    }
    if (block1 == null || block2 == null) {
        return false
    }
    return block1.id == block2.id && block1.type == block2.type && block1.content == block2.content
}

private fun equals(blockList1: List<Block>?, blockList2: List<Block>?): Boolean {
    if (blockList1 === blockList2) {
        return true
    }
    if (blockList1 == null || blockList2 == null || blockList1.size != blockList2.size) {
        return false
    }
    val size = blockList1.size
    var i = 0
    while (i < size) {
        if (!equals(blockList1[i], blockList2[i])) {
            return false
        }
        i++
    }
    return true
}

@JvmName("profileStateToText")
private fun ProfileState.toText(): String {
    try {
        return StringBuilder().append("[fontSize]\n").append(fontSize).append('\n').append("[fontWeight]\n")
            .append(fontWeight).append('\n').append("[fontFamily]\n").append(fontFamily.escapeJava()).append('\n')
            .append("[backgroundAlpha]\n").append(backgroundAlpha).append('\n').append("[dataPath]\n")
            .append(dataPath.escapeJava()).append('\n').append("[markdownPath]\n").append(markdownPath.escapeJava())
            .append('\n').append("[imageCache]\n").append(imageCache).append('\n').append("[copyWhenClick]\n")
            .append(copyWhenClick).append('\n').append("[colorTheme]\n").append(colorTheme.escapeJava()).append('\n')
            .append("[language]\n").append(language.escapeJava()).append('\n').append("[tooltip]\n").append(tooltip)
            .append('\n').append("[transition]\n").append(transition).toString()
    } catch (e: Exception) {
        log.error("Failed to convert profile state to text", e)
        return ""
    }
}

@JvmName("notesToText")
private fun List<Note>.toText(): String {
    try {
        val stringBuilder = StringBuilder()
        for (i in this.indices) {
            val note = this[i]
            stringBuilder.append('[').append(i).append(".id]\n").append(note.id.escapeJava()).append('\n').append('[')
                .append(i).append(".createTime]\n").append(note.createTime).append('\n').append('[').append(i)
                .append(".updateTime]\n").append(note.updateTime).append('\n').append('[').append(i)
                .append(".description]\n").append(note.description.escapeJava()).append('\n').append('[').append(i)
                .append(".visible]\n").append(note.visible).append('\n').append('[').append(i).append(".style]\n")
                .append(note.style.escapeJava()).append('\n').append('[').append(i).append(".width]\n").append(note.width)
                .append('\n').append('[').append(i).append(".height]\n").append(note.height).append('\n').append('[')
                .append(i).append(".alwaysOnTop]\n").append(note.alwaysOnTop).append('\n').append('[').append(i)
                .append(".x]\n").append(note.x).append('\n').append('[').append(i).append(".y]\n").append(note.y)
                .append('\n').append('[').append(i).append(".mode]\n").append(note.mode).append('\n').append('[').append(i)
                .append(".position1]\n").append(note.position1).append('\n').append('[').append(i).append(".position2]\n")
                .append(note.position2).append('\n').append('[').append(i).append(".position3]\n").append(note.position3)
                .append('\n')
        }
        return stringBuilder.toString()
    } catch (e: Exception) {
        log.error("Failed to convert notes to text", e)
        return ""
    }
}

@JvmName("blocksToText")
private fun List<Block>.toText(): String {
    try {
        val stringBuilder = StringBuilder()
        for (block in this) {
            stringBuilder.append('[').append(block.id).append(']').append(block.type.description.escapeJava()).append('\n')
                .append(block.content.escapeJava()).append('\n')
        }
        return stringBuilder.toString()
    } catch (e: Exception) {
        log.error("Failed to convert blocks to text", e)
        return ""
    }
}

private fun Path.readKeyValues(charset: Charset): List<Pair<String, String>> {
    if (!this.exists() || this.isDirectory() || !this.isReadable()) {
        return emptyList()
    }
    val list = ArrayList<Pair<String, String>>()
    this.bufferedReader(charset = charset).useLines { lines ->
        var key: String? = null
        for (line in lines) {
            if (key == null) {
                key = line.unescapeJava()
            } else {
                list.add(key to line.unescapeJava())
                key = null
            }
        }
    }
    return list
}

private fun Path.readAsProfileState(charset: Charset, defaultProfileState: ProfileState): ProfileState {
    try {
        val list = this.readKeyValues(charset)
        if (list.isEmpty()) {
            return defaultProfileState
        }
        var fontSize = defaultProfileState.fontSize
        var fontWeight = defaultProfileState.fontWeight
        var fontFamily = defaultProfileState.fontFamily
        var backgroundAlpha = defaultProfileState.backgroundAlpha
        var dataPath = defaultProfileState.dataPath
        var markdownPath = defaultProfileState.markdownPath
        var imageCache = defaultProfileState.imageCache
        var copyWhenClick = defaultProfileState.copyWhenClick
        var colorTheme = defaultProfileState.colorTheme
        var language = defaultProfileState.language
        var tooltip = defaultProfileState.tooltip
        var transition = defaultProfileState.transition
        for ((key, value) in list) {
            val propertyName = key.substring(1, key.length - 1)
            when (propertyName) {
                "fontSize" -> {
                    fontSize = value.toIntOrNull() ?: fontSize
                }

                "fontWeight" -> {
                    fontWeight = value.toIntOrNull() ?: fontWeight
                }

                "fontFamily" -> {
                    fontFamily = value
                }

                "backgroundAlpha" -> {
                    backgroundAlpha = value.toIntOrNull() ?: backgroundAlpha
                }

                "dataPath" -> {
                    dataPath = value
                }

                "markdownPath" -> {
                    markdownPath = value
                }

                "imageCache" -> {
                    imageCache = value.toIntOrNull() ?: imageCache
                }

                "copyWhenClick" -> {
                    copyWhenClick = value.toBooleanStrictOrNull() ?: true
                }

                "colorTheme" -> {
                    colorTheme = value
                }

                "language" -> {
                    language = value
                }

                "tooltip" -> {
                    tooltip = value.toBooleanStrictOrNull() ?: true
                }

                "transition" -> {
                    transition = value.toBooleanStrictOrNull() ?: true
                }

                else -> {}
            }
        }
        return ProfileState(
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            backgroundAlpha = backgroundAlpha,
            dataPath = dataPath,
            markdownPath = markdownPath,
            imageCache = imageCache,
            copyWhenClick = copyWhenClick,
            colorTheme = colorTheme,
            language = language,
            tooltip = tooltip,
            transition = transition,
        )
    } catch (e: Exception) {
        log.error("Failed to read profile state from $this", e)
        return defaultProfileState
    }
}

private fun Path.readAsNotes(charset: Charset): List<Note> {
    try {
        val list = this.readKeyValues(charset)
        if (list.isEmpty()) {
            return emptyList()
        }
        val idList = Array<String?>(list.size) { null }
        val idToIndex = HashMap<String, Int>()
        val createTimeArray = LongArray(list.size) { 0L }
        val updateTimeArray = LongArray(list.size) { 0L }
        val descriptionArray = Array(list.size) { "" }
        val visibleArray = BooleanArray(list.size) { false }
        val styleArray = Array(list.size) { "" }
        val widthArray = IntArray(list.size) { 360 }
        val heightArray = IntArray(list.size) { 360 }
        val alwaysOnTopArray = BooleanArray(list.size) { false }
        val xArray = IntArray(list.size) { -1 }
        val yArray = IntArray(list.size) { -1 }
        val modeArray = IntArray(list.size) { 0 }
        val position1Array = IntArray(list.size) { 0 }
        val position2Array = IntArray(list.size) { 0 }
        val position3Array = IntArray(list.size) { 100 }
        for ((key, value) in list) {
            val dotIndex = key.indexOf(".")
            if (dotIndex == -1) {
                continue
            }
            val index = key.substring(1, dotIndex).toIntOrNull() ?: continue
            val propertyName = key.substring(dotIndex + 1, key.length - 1)
            when (propertyName) {
                "id" -> {
                    idList[index] = value
                    idToIndex[value] = index
                }

                "createTime" -> {
                    val longValue = value.toLongOrNull()
                    if (longValue != null) {
                        createTimeArray[index] = longValue
                    }
                }

                "updateTime" -> {
                    val longValue = value.toLongOrNull()
                    if (longValue != null) {
                        updateTimeArray[index] = longValue
                    }
                }

                "description" -> {
                    descriptionArray[index] = value
                }

                "visible" -> {
                    val booleanValue = value.toBooleanStrictOrNull()
                    if (booleanValue != null) {
                        visibleArray[index] = booleanValue
                    }
                }

                "style" -> {
                    styleArray[index] = value
                }

                "width" -> {
                    val intValue = value.toIntOrNull()
                    if (intValue != null) {
                        widthArray[index] = intValue
                    }
                }

                "height" -> {
                    val intValue = value.toIntOrNull()
                    if (intValue != null) {
                        heightArray[index] = intValue
                    }
                }

                "alwaysOnTop" -> {
                    val booleanValue = value.toBooleanStrictOrNull()
                    if (booleanValue != null) {
                        alwaysOnTopArray[index] = booleanValue
                    }
                }

                "x" -> {
                    val intValue = value.toIntOrNull()
                    if (intValue != null) {
                        xArray[index] = intValue
                    }
                }

                "y" -> {
                    val intValue = value.toIntOrNull()
                    if (intValue != null) {
                        yArray[index] = intValue
                    }
                }

                "mode" -> {
                    val intValue = value.toIntOrNull()
                    if (intValue != null) {
                        modeArray[index] = intValue
                    }
                }

                "position1" -> {
                    val intValue = value.toIntOrNull()
                    if (intValue != null) {
                        position1Array[index] = intValue
                    }
                }

                "position2" -> {
                    val intValue = value.toIntOrNull()
                    if (intValue != null) {
                        position2Array[index] = intValue
                    }
                }

                "position3" -> {
                    val intValue = value.toIntOrNull()
                    if (intValue != null && intValue >= 10) {
                        position3Array[index] = intValue
                    }
                }

                else -> {}
            }
        }
        return idToIndex.entries.map {
            Note(
                id = it.key,
                createTime = createTimeArray[it.value],
                updateTime = updateTimeArray[it.value],
                description = descriptionArray[it.value],
                visible = visibleArray[it.value],
                style = styleArray[it.value],
                width = widthArray[it.value],
                height = heightArray[it.value],
                alwaysOnTop = alwaysOnTopArray[it.value],
                x = xArray[it.value],
                y = yArray[it.value],
                mode = modeArray[it.value],
                position1 = position1Array[it.value],
                position2 = position2Array[it.value],
                position3 = position3Array[it.value],
            )
        }
    } catch (e: Exception) {
        log.error("Failed to read notes from $this", e)
        return emptyList()
    }
}

private fun Path.readAsBlocks(charset: Charset): List<Block> {
    try {
        val list = this.readKeyValues(charset)
        if (list.isEmpty()) {
            return emptyList()
        }
        val blocks = ArrayList<Block>(list.size)
        for ((key, value) in list) {
            val endIndex = key.indexOf("]")
            if (endIndex == -1) {
                continue
            }
            val id = key.substring(1, endIndex).toIntOrNull() ?: continue
            val typeName = key.substring(endIndex + 1).trim().lowercase(Locale.getDefault())
            val type = when (typeName) {
                BlockType.TEXT.description -> BlockType.TEXT
                BlockType.BOLD.description -> BlockType.BOLD
                BlockType.ITALIC.description -> BlockType.ITALIC
                BlockType.UNDERLINE.description -> BlockType.UNDERLINE
                BlockType.LINE_THROUGH.description -> BlockType.LINE_THROUGH
                BlockType.IMAGE.description -> BlockType.IMAGE
                else -> BlockType.TEXT
            }
            blocks.add(Block(type, value, id))
        }
        return blocks
    } catch (e: Exception) {
        log.error("Failed to read blocks from $this", e)
        return emptyList()
    }
}