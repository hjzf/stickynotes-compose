package logic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import kotlin.io.path.Path

const val APP_NAME = "stickynotes"

data class ProfileState(
    val fontSize: Int = 14,
    val fontWeight: Int = 420,
    val fontFamily: String = FontFamily.Monospace.name,
    val backgroundAlpha: Int = 255,
    val dataPath: String = Path(System.getProperty("user.home"))
        .resolve("Desktop")
        .resolve(APP_NAME)
        .resolve("data").toString(),
    val markdownPath: String = Path(System.getProperty("user.home"))
        .resolve("Desktop").toString(),
    val imageCache: Int = 100,
    val copyWhenClick: Boolean = true,
    val colorTheme: String = "dark",
    val language: String = "zh",
    val tooltip: Boolean = true,
    val transition: Boolean = true,
)

data class ApplicationLocalization(
    val stickynotes: String = "Sticky Notes",
    val settings: String = "Settings",
    val closeWindow: String = "Close window",
    val back: String = "Back",
    val search: String = "Search",
    val clear: String = "Clear",
    val menu: String = "Menu",
    val newNote: String = "New note",
    val openNote: String = "Open note",
    val closeNote: String = "Close note",
    val deleteNote: String = "Delete note",
    val exportMarkdown: String = "Export markdown",
    val notesList: String = "Notes list",
    val plainTextMode: String = "Plain text mode",
    val imageTextMode: String = "Image & text mode",
    val injectFromClipboard: String = "Inject from clipboard",
    val pin: String = "Pin",
    val unpin: String = "Unpin",
    val more: String = "More",
    val pleaseInput: String = "Please input",
    val takeNote: String = "Take a note",
    val notSupported: String = "Not supported",
    val bold: String = "Bold",
    val italic: String = "Italic",
    val underline: String = "Underline",
    val strikethrough: String = "Strikethrough",
    val toggleBullets: String = "Toggle Bullets",
    val addImage: String = "Add Image",
    val copyText: String = "Copy text",
    val textCopied: String = "Text copied",
    val deleteText: String = "Delete text",
    val viewImage: String = "View image",
    val copyImage: String = "Copy image",
    val deleteImage: String = "Delete image",
    val imageNotExist: String = "Image does not exist",
    val imageOpenFailed: String = "Failed to open the image",
    val imageOpenSucceeded: String = "The image was opened successfully",
    val imageCopyFailed: String = "Failed to copy the image",
    val imageCopySucceeded: String = "The image was copied successfully",
    val textCopyFailed: String = "Failed to copy the text",
    val textCopySucceeded: String = "The text was copied successfully",
    val inputting: String = "Inputting",
    val updating: String = "Updating",
    val saved: String = "Saved",
    val language: String = "Language",
    val fontSize: String = "Font Size",
    val fontWeight: String = "Font Weight",
    val fontFamily: String = "Font Family",
    val colorTheme: String = "Color Theme",
    val light: String = "Light",
    val dark: String = "Dark",
    val backgroundAlpha: String = "Background Alpha",
    val imageCache: String = "Image Cache",
    val dataPath: String = "Data Path",
    val markdown: String = "Markdown",
    val copyWhenClick: String = "Copy When Click",
    val tooltip: String = "Tooltip",
    val transition: String = "Transition",
    val updateIndex: String = "Update index",
    val minimize: String = "Minimize",
    val maximize: String = "maximize",
    val exitApplication: String = "Exit application",
    val markAs: String = "Mark as",
    val ok: String = "OK",
    val delete: String = "Delete",
    val cancel: String = "Cancel",
    val confirmDelete: String = "Delete",
    val confirmDeleteThisText: String = "Are you sure you want to permanently delete this text?",
    val minimizeToTray: String = "Minimize to tray",
)

data class Note(
    val id: String = "",
    val description: String = "",
    val visible: Boolean = false,
    val style: String = "green",
    val width: Int = 360,
    val height: Int = 360,
    val alwaysOnTop: Boolean = false,
    val x: Int = -1,
    val y: Int = -1,
    val mode: Int = 0,
    val createTime: Long = 0L,
    val updateTime: Long = 0L,
    val position1: Int = 0,
    val position2: Int = 0,
    val position3: Int = 100,
)

data class NoteCard(
    val key: String = "",
    val noteId: String = "",
    val position1: Int = 0,
    val position2: Int = 0,
    val description: String = "",
    val visible: Boolean = false,
    val style: String = "green",
    val updateTime: String = "",
)

data class NoteColor(
    val base: Color,
    val header: Color,
    val content: Color,
    val border: Color,
    val font: Color,
    val icon: Color,
    val card: Color,
    val overlap: Color,
)

enum class BlockType(val description: String) {
    TEXT("text"),
    BOLD("b"),
    UNDERLINE("u"),
    ITALIC("i"),
    LINE_THROUGH("rm"),
    IMAGE("image"),
}

data class Block(val type: BlockType, val content: String, val id: Int)