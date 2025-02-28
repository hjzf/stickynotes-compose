package logic

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.flow.MutableSharedFlow
import org.jetbrains.skia.Image.Companion.makeFromEncoded
import tool.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.io.path.*

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
        val file = File(key)
        val absoluteFile = if (file.isAbsolute) file else File(_profileState.dataPath, key)
        if (absoluteFile.exists() && absoluteFile.isFile && absoluteFile.canRead()) {
            try {
                val skiaImage = makeFromEncoded(absoluteFile.readBytes())
                composeImageBitmap = skiaImage.toComposeImageBitmap()
                _imageCache[key] = composeImageBitmap
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return composeImageBitmap
    }

    fun loadProfileState(profileFilePath: Path, defaultDataPath: Path): ProfileState {
        _profileState = profileStateFromKeyValues(
            profileFilePath.toFile().readKeyValues(),
            ProfileState(dataPath = defaultDataPath.toString())
        )
        _imageCache = LRUCache(_profileState.imageCache)
        return _profileState
    }

    fun updateFontSize(fontSize: Int, profileFilePath: Path): ProfileState {
        if (fontSize != _profileState.fontSize) {
            _profileState = _profileState.copy(fontSize = fontSize)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    fun updateFontWeight(fontWeight: Int, profileFilePath: Path): ProfileState {
        if (fontWeight != _profileState.fontWeight) {
            _profileState = _profileState.copy(fontWeight = fontWeight)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    fun updateFontFamily(fontFamily: String, profileFilePath: Path): ProfileState {
        if (fontFamily != _profileState.fontFamily) {
            _profileState = _profileState.copy(fontFamily = fontFamily)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    fun updateBackgroundAlpha(backgroundAlpha: Int, profileFilePath: Path): ProfileState {
        if (backgroundAlpha != _profileState.backgroundAlpha) {
            _profileState = _profileState.copy(backgroundAlpha = backgroundAlpha)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    suspend fun updateDataPath(dataPath: String, profileFilePath: Path): ProfileState {
        if (dataPath != _profileState.dataPath) {
            _profileState = _profileState.copy(dataPath = dataPath)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
            dataPathVersionFlow.emit(System.currentTimeMillis())
        }
        return _profileState
    }

    fun updateMarkdownPath(markdownPath: String, profileFilePath: Path): ProfileState {
        if (markdownPath != _profileState.markdownPath) {
            _profileState = _profileState.copy(markdownPath = markdownPath)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    fun updateImageCache(imageCache: Int, profileFilePath: Path): ProfileState {
        if (imageCache != _profileState.imageCache) {
            _profileState = _profileState.copy(imageCache = imageCache)
            _imageCache = LRUCache(_profileState.imageCache)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    fun updateCopyWhenClick(copyWhenClick: Boolean, profileFilePath: Path): ProfileState {
        if (copyWhenClick != _profileState.copyWhenClick) {
            _profileState = _profileState.copy(copyWhenClick = copyWhenClick)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    fun updateColorTheme(colorTheme: String, profileFilePath: Path): ProfileState {
        if (colorTheme != _profileState.colorTheme) {
            _profileState = _profileState.copy(colorTheme = colorTheme)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    fun updateLanguage(language: String, profileFilePath: Path): ProfileState {
        if (language != _profileState.language) {
            _profileState = _profileState.copy(language = language)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    fun updateTooltip(tooltip: Boolean, profileFilePath: Path): ProfileState {
        if (tooltip != _profileState.tooltip) {
            _profileState = _profileState.copy(tooltip = tooltip)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    fun updateTransition(transition: Boolean, profileFilePath: Path): ProfileState {
        if (transition != _profileState.transition) {
            _profileState = _profileState.copy(transition = transition)
            profileFilePath.toFile().writeKeyValues(_profileState.toKeyValues())
        }
        return _profileState
    }

    suspend fun addNewNote(): Note {
        val notes = loadNotes().toMutableList()
        val newNote = Note(
            id = UUID.randomUUID().toString().replace("-", ""),
            visible = true,
            updateTime = currentTimeAsTimestamp(),
            createTime = currentTimeAsTimestamp()
        )
        notes.add(newNote)
        saveNotes(notes)
        saveBlocks(newNote.id, emptyList())
        windowStateVersionFlow.emit(System.currentTimeMillis())
        return newNote
    }

    suspend fun deleteNote(id: String) {
        val notes = loadNotes().toMutableList()
        val index = notes.indexOfFirst { it.id == id }
        if (index >= 0) {
            notes.removeAt(index)
            saveNotes(notes)
            windowStateVersionFlow.emit(System.currentTimeMillis())
        }
    }

    suspend fun updateNoteVisible(id: String, visible: Boolean) {
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
    }

    suspend fun updateNoteVisibleAndPosition(id: String, visible: Boolean, position1: Int, position2: Int) {
        val notes = loadNotes().toMutableList()
        if (id.isNotEmpty()) {
            val index = notes.indexOfFirst { it.id == id }
            if (index >= 0) {
                if (notes[index].visible != visible) {
                    notes[index] = notes[index].copy(visible = visible, position1 = position1, position2 = position2)
                    saveNotes(notes)
                    windowStateVersionFlow.emit(System.currentTimeMillis())
                }
            }
        }
    }

    fun updateNotePosition(id: String, position1: Int, position2: Int, position3: Int) {
        val notes = loadNotes().toMutableList()
        if (id.isNotEmpty()) {
            val index = notes.indexOfFirst { it.id == id }
            if (index >= 0) {
                if (notes[index].position1 != position1 || notes[index].position2 != position2 || notes[index].position3 != position3) {
                    notes[index] = notes[index].copy(
                        position1 = position1,
                        position2 = position2,
                        position3 = position3
                    )
                    saveNotes(notes)
                }
            }
        }
    }

    suspend fun updateNoteStyle(id: String, style: String) {
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
    }

    fun updateNoteAlwaysOnTop(id: String, alwaysOnTop: Boolean) {
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
    }

    fun updateNoteWidthAndHeight(id: String, width: Int, height: Int) {
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
    }

    fun updateNoteXAndY(id: String, x: Int, y: Int) {
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
    }

    fun updateNoteMode(id: String, mode: Int) {
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
    }

    suspend fun updateNoteContent(id: String, blocks: List<Block>) {
        val notes = loadNotes().toMutableList()
        if (id.isNotEmpty()) {
            val index = notes.indexOfFirst { it.id == id }
            if (index >= 0) {
                val note = notes[index]
                if (!equals(blocks, loadBlocks(note.id))) {
                    notes[index] = note.copy(
                        description = calculateDescription(blocks),
                        updateTime = currentTimeAsTimestamp()
                    )
                    saveNotes(notes)
                    saveBlocks(id, blocks)
                    noteContentVersionFlow.emit(System.currentTimeMillis())
                }
            }
        }
    }

    private fun calculateDescription(blocks: List<Block>): String {
        val stringBuilder = StringBuilder()
        for (i in 0..minOf(5, blocks.size - 1)) {
            stringBuilder.append(blocks[i].content)
        }
        return stringBuilder.toString()
    }

    fun loadNotes(): List<Note> {
        return notesFromKeyValues((Path(_profileState.dataPath) / NOTES_TXT).toFile().readKeyValues())
    }

    private fun saveNotes(notes: MutableList<Note>) {
        (Path(_profileState.dataPath) / NOTES_TXT).toFile().writeKeyValues(notes.toKeyValues())
    }

    fun loadBlocks(id: String): List<Block> {
        return blocksFromKeyValues((Path(_profileState.dataPath) / "${id}.txt").toFile().readKeyValues())
    }

    private fun saveBlocks(id: String, blocks: List<Block>) {
        (Path(_profileState.dataPath) / "${id}.txt").toFile().writeKeyValues(blocks.toKeyValues())
    }

    private fun String.escape(): String {
        return this.lines().joinToString("\n") {
            if (it.startsWith("#") || it.startsWith("\\")) {
                "\\$it"
            } else {
                it
            }
        }
    }

    fun loadBlocksAsRawText(id: String): String {
        val blocks = loadBlocks(id)
        if (blocks.isEmpty()) {
            return ""
        }
        if (blocks.size == 1) {
            val singleBlock = blocks[0]
            return if (singleBlock.type in arrayOf(
                    BlockType.TEXT,
                    BlockType.BOLD,
                    BlockType.ITALIC,
                    BlockType.UNDERLINE,
                    BlockType.LINE_THROUGH
                )
            ) {
                singleBlock.content.escape()
            } else {
                "# ${singleBlock.type.description}\n${singleBlock.content.escape()}"
            }
        }
        val builder = StringBuilder()
        val firstBlock = blocks[0]
        if (firstBlock.type in arrayOf(
                BlockType.TEXT,
                BlockType.BOLD,
                BlockType.ITALIC,
                BlockType.UNDERLINE,
                BlockType.LINE_THROUGH
            )
        ) {
            builder.append("${firstBlock.content.escape()}\n")
        } else {
            builder.append("# ${firstBlock.type.description}\n${firstBlock.content.escape()}\n")
        }
        for (i in 1..(blocks.size - 2)) {
            val block = blocks[i]
            builder.append("# ${block.type.description}\n${block.content.escape()}\n")
        }
        builder.append("# ${blocks[blocks.size - 1].type.description}\n${blocks[blocks.size - 1].content.escape()}")
        return builder.toString()
    }

    suspend fun saveRawTextAsBlocks(id: String, rawText: String) {
        if (rawText.isEmpty()) {
            updateNoteContent(id, emptyList())
        }
        val lines = rawText.lines()
        if (lines.isEmpty()) {
            updateNoteContent(id, emptyList())
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
    }

    fun search(target: String): List<NoteCard> {
        if (target.isEmpty()) {
            return loadNotes().sortedByDescending { it.updateTime }.map {
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
        val notes = loadNotes().sortedByDescending { it.updateTime }
        if (notes.isEmpty()) {
            return emptyList()
        }
        val noteCards = ArrayList<NoteCard>(notes.size)
        for (note in notes) {
            val updateTimeAsString = formatTimestamp(note.updateTime)
            val blocks = loadBlocks(note.id)
            for ((blockIndex, block) in blocks.withIndex()) {
                val index = block.content.indexOf(target, ignoreCase = true)
                if (index >= 0) {
                    noteCards.add(
                        NoteCard(
                            key = "${note.id}_${blockIndex}",
                            noteId = note.id,
                            position1 = blockIndex,
                            position2 = 0,
                            description = block.content.substring(if (index >= 8) index - 8 else 0),
                            visible = note.visible,
                            style = note.style,
                            updateTime = updateTimeAsString,
                        )
                    )
                }
            }
            if (
                arrayOf(
                    updateTimeAsString,
                    formatTimestamp(note.updateTime, "yyyy-MM-dd"),
                    formatTimestamp(note.updateTime, "yyyy MM dd"),
                    formatTimestamp(note.updateTime, "yyyy.MM.dd"),
                    formatTimestamp(note.updateTime, "yyyy年MM月dd日"),
                    formatTimestamp(note.updateTime, "yyyy/MM/d"),
                    formatTimestamp(note.updateTime, "yyyy-MM-d"),
                    formatTimestamp(note.updateTime, "yyyy MM d"),
                    formatTimestamp(note.updateTime, "yyyy.MM.d"),
                    formatTimestamp(note.updateTime, "yyyy年MM月d日"),
                ).any { it.indexOf(target, ignoreCase = true) >= 0 }
            ) {
                noteCards.add(
                    NoteCard(
                        key = "${note.id}_",
                        noteId = note.id,
                        position1 = note.position1,
                        position2 = note.position2,
                        description = note.description,
                        visible = note.visible,
                        style = note.style,
                        updateTime = updateTimeAsString,
                    )
                )
            }
        }
        return noteCards
    }

    fun exportMarkdown(id: String): Boolean {
        var ok: Boolean
        try {
            val markdownFilePath = Path(_profileState.markdownPath) / "${APP_NAME}_${id}" / "index.md"
            val markdownFile = markdownFilePath.toFile()
            if (!markdownFile.exists()) {
                markdownFile.parentFile.mkdirs()
            }
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
                        val path = Path(block.content)
                        val sourcePath = if (path.isAbsolute) {
                            path
                        } else {
                            Path(_profileState.dataPath) / block.content
                        }
                        val fileName = sourcePath.fileName.name
                        val targetPath = imagesPath / fileName
                        try {
                            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING)
                        } catch (exception: Exception) {
                            exception.printStackTrace()
                        }
                        markdownTextList.add("![${fileName}](./${images}/${fileName})")
                    }
                }
            }
            markdownFile.writeText(markdownTextList.joinToString("\n\n"))
            ok = true
        } catch (e: Exception) {
            e.printStackTrace()
            ok = false
        }
        return ok
    }

}

fun ProfileState.toKeyValues(): Map<String, String> {
    return mapOf(
        "[fontSize]" to fontSize.toString(),
        "[fontWeight]" to fontWeight.toString(),
        "[fontFamily]" to fontFamily,
        "[backgroundAlpha]" to backgroundAlpha.toString(),
        "[dataPath]" to dataPath,
        "[markdownPath]" to markdownPath,
        "[imageCache]" to imageCache.toString(),
        "[copyWhenClick]" to copyWhenClick.toString(),
        "[colorTheme]" to colorTheme,
        "[language]" to language,
        "[tooltip]" to tooltip.toString(),
        "[transition]" to transition.toString(),
    )
}

fun profileStateFromKeyValues(kv: Map<String, String>, defaultProfileState: ProfileState): ProfileState {
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
    for (entry in kv.entries) {
        val key = entry.key
        val value = entry.value
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
}

@JvmName("notesToKeyValues")
fun List<Note>.toKeyValues(): Map<String, String> {
    val kv = mutableMapOf<String, String>()
    for (i in this.indices) {
        val note = this[i]
        kv["[${i}.id]"] = note.id
        kv["[${i}.createTime]"] = note.createTime.toString()
        kv["[${i}.updateTime]"] = note.updateTime.toString()
        kv["[${i}.description]"] = note.description
        kv["[${i}.visible]"] = note.visible.toString()
        kv["[${i}.style]"] = note.style
        kv["[${i}.width]"] = note.width.toString()
        kv["[${i}.height]"] = note.height.toString()
        kv["[${i}.alwaysOnTop]"] = note.alwaysOnTop.toString()
        kv["[${i}.x]"] = note.x.toString()
        kv["[${i}.y]"] = note.y.toString()
        kv["[${i}.mode]"] = note.mode.toString()
        kv["[${i}.position1]"] = note.position1.toString()
        kv["[${i}.position2]"] = note.position2.toString()
        kv["[${i}.position3]"] = note.position3.toString()
    }
    return kv
}

fun notesFromKeyValues(kv: Map<String, String>): List<Note> {
    val idList = Array<String?>(kv.size) { null }
    val idToIndex = HashMap<String, Int>()
    val createTimeArray = LongArray(kv.size) { 0L }
    val updateTimeArray = LongArray(kv.size) { 0L }
    val descriptionArray = Array(kv.size) { "" }
    val visibleArray = BooleanArray(kv.size) { false }
    val styleArray = Array(kv.size) { "" }
    val widthArray = IntArray(kv.size) { 360 }
    val heightArray = IntArray(kv.size) { 360 }
    val alwaysOnTopArray = BooleanArray(kv.size) { false }
    val xArray = IntArray(kv.size) { -1 }
    val yArray = IntArray(kv.size) { -1 }
    val modeArray = IntArray(kv.size) { 0 }
    val position1Array = IntArray(kv.size) { 0 }
    val position2Array = IntArray(kv.size) { 0 }
    val position3Array = IntArray(kv.size) { 100 }
    for (entry in kv.entries) {
        val key = entry.key
        val dotIndex = key.indexOf(".")
        if (dotIndex == -1) {
            continue
        }
        val index = key.substring(1, dotIndex).toIntOrNull() ?: continue
        val propertyName = key.substring(dotIndex + 1, key.length - 1)
        val value = entry.value
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
}

@JvmName("blocksToKeyValues")
fun List<Block>.toKeyValues(): Map<String, String> {
    val kv = mutableMapOf<String, String>()
    for ((index, block) in this.withIndex()) {
        kv["[${index}]${block.type.description}"] = block.content
    }
    return kv
}

fun equals(block1: Block?, block2: Block?): Boolean {
    if (block1 == null && block2 == null) {
        return true
    }
    if (block1 == null) {
        return false
    }
    if (block2 == null) {
        return false
    }
    if (block1 === block2) {
        return true
    }
    return block1.id == block2.id && block1.type == block2.type && block1.content == block2.content
}

fun equals(blockList1: List<Block>?, blockList2: List<Block>?): Boolean {
    if (blockList1 == null && blockList2 == null) {
        return true
    }
    if (blockList1 == null) {
        return false
    }
    if (blockList2 == null) {
        return false
    }
    if (blockList1 === blockList2) {
        return true
    }
    if (blockList1.size != blockList2.size) {
        return false
    }
    for (index in blockList1.indices) {
        if (!equals(blockList1[index], blockList2[index])) {
            return false
        }
    }
    return true
}

fun blocksFromKeyValues(kv: Map<String, String>): List<Block> {
    val treeMap = TreeMap<Int, Block> { a, b -> a - b }
    for (entry in kv.entries) {
        val key = entry.key
        val endIndex = key.indexOf("]")
        if (endIndex == -1) {
            continue
        }
        val index = key.substring(1, endIndex).toIntOrNull() ?: continue
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
        treeMap[index] = Block(type, entry.value, index)
    }
    if (treeMap.isEmpty()) {
        return emptyList()
    }
    return treeMap.values.toList()
}