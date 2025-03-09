package logic

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.PlatformLocalization
import androidx.compose.ui.unit.dp
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val log: Logger = LoggerFactory.getLogger("DefaultValues")

val defaultThemeColors = Colors(
    primary = Color(0xff3574f0),
    primaryVariant = Color(0xff76a6ff),
    secondary = Color(0xff09a100),
    secondaryVariant = Color(0xff6de366),
    background = Color(0xffffffff),
    surface = Color(0xffffffff),
    error = Color(0xffff0000),
    onPrimary = Color(0xffffffff),
    onSecondary = Color(0xffffffff),
    onBackground = Color(0xffffffff),
    onSurface = Color(0xffffffff),
    onError = Color(0xffffffff),
    isLight = true
)
val defaultThemeShapes = Shapes(
    small = RoundedCornerShape(0.dp),
    medium = RoundedCornerShape(0.dp),
    large = RoundedCornerShape(0.dp)
)
val defaultTextFieldLocalization = object : PlatformLocalization {
    override val copy = "Copy"
    override val cut = "Cut"
    override val paste = "Paste"
    override val selectAll = "Select All"
}
val defaultApplicationLocalization = ApplicationLocalization()
val textFieldLocalizationMap = mapOf(
    "en" to defaultTextFieldLocalization,
    "zh" to object : PlatformLocalization {
        override val copy = "复制"
        override val cut = "剪切"
        override val paste = "粘贴"
        override val selectAll = "全选"
    }
)
val applicationLocalizationMap = mapOf(
    "en" to defaultApplicationLocalization,
    "zh" to ApplicationLocalization(
        stickynotes = "便笺",
        settings = "设置",
        closeWindow = "关闭窗口",
        back = "返回",
        search = "搜索",
        clear = "清空",
        menu = "菜单",
        newNote = "新建笔记",
        openNote = "打开笔记",
        closeNote = "关闭笔记",
        deleteNote = "删除笔记",
        exportMarkdown = "导出 Markdown",
        notesList = "便笺列表",
        plainTextMode = "纯文本模式",
        imageTextMode = "图文模式",
        injectFromClipboard = "从剪贴板注入",
        pin = "置顶窗口",
        unpin = "取消置顶",
        more = "更多",
        pleaseInput = "请输入",
        takeNote = "记笔记",
        notSupported = "暂不支持",
        bold = "加粗",
        italic = "倾斜",
        underline = "下划线",
        strikethrough = "删除线",
        toggleBullets = "切换项目符号",
        addImage = "添加图像",
        copyText = "复制文本",
        textCopied = "文本已复制",
        deleteText = "删除文本",
        viewImage = "查看图像",
        copyImage = "复制图像",
        deleteImage = "删除图像",
        imageNotExist = "图像不存在",
        imageOpenFailed = "图像打开失败",
        imageOpenSucceeded = "图像打开成功",
        imageCopyFailed = "图像复制失败",
        imageCopySucceeded = "图像复制成功",
        textCopyFailed = "文本复制失败",
        textCopySucceeded = "文本复制成功",
        inputting = "正在输入",
        updating = "正在更新",
        saved = "已保存",
        language = "语言",
        fontSize = "字体大小",
        fontWeight = "字体粗细",
        fontFamily = "字体类型",
        colorTheme = "颜色主题",
        light = "浅色",
        dark = "深色",
        backgroundAlpha = "背景透明",
        imageCache = "图片缓存",
        dataPath = "数据路径",
        markdown = "Markdown",
        copyWhenClick = "点击复制",
        tooltip = "悬浮提示",
        transition = "过渡动画",
        updateIndex = "更新索引",
        minimize = "最小化",
        maximize = "最大化",
        exitApplication = "退出程序",
        markAs = "标记为",
        ok = "确定",
        delete = "删除",
        cancel = "取消",
        confirmDelete = "确认删除",
        confirmDeleteThisText = "确定要永久删除这段文本吗?",
        minimizeToTray = "隐藏到托盘",
    ),
)
private val noteColorStyles = mapOf(
    "light" to mapOf(
        "yellow" to NoteColor(
            base = Color(0xffffe66e),
            header = Color(0xfffff2ab),
            content = Color(0xfffff7d1),
            border = Color(0xffe5debc),
            font = Color(0xff000000),
            icon = Color(0xff636363),
            card = Color(0xfffff7d1),
            overlap = Color(0xffe5debc),
        ),
        "green" to NoteColor(
            base = Color(0xffa1ef9b),
            header = Color(0xffcbf1c4),
            content = Color(0xffe4f9e0),
            border = Color(0xffcde0c9),
            font = Color(0xff000000),
            icon = Color(0xff636363),
            card = Color(0xffe4f9e0),
            overlap = Color(0xffcde0c9),
        ),
        "pink" to NoteColor(
            base = Color(0xffffafdf),
            header = Color(0xffffcce5),
            content = Color(0xffffe4f1),
            border = Color(0xffe5cdd8),
            font = Color(0xff000000),
            icon = Color(0xff636363),
            card = Color(0xffffe4f1),
            overlap = Color(0xffe5cdd8),
        ),
        "purple" to NoteColor(
            base = Color(0xffd7afff),
            header = Color(0xffe7cfff),
            content = Color(0xfff2e6ff),
            border = Color(0xffd9cfe5),
            font = Color(0xff000000),
            icon = Color(0xff636363),
            card = Color(0xfff2e6ff),
            overlap = Color(0xffd9cfe5),
        ),
        "blue" to NoteColor(
            base = Color(0xff9edfff),
            header = Color(0xffcde9ff),
            content = Color(0xffe2f1ff),
            border = Color(0xffcbd8e5),
            font = Color(0xff000000),
            icon = Color(0xff636363),
            card = Color(0xffe2f1ff),
            overlap = Color(0xffcbd8e5),
        ),
        "gray" to NoteColor(
            base = Color(0xffe0e0e0),
            header = Color(0xffe1dfdd),
            content = Color(0xfff3f2f1),
            border = Color(0xffdad9d8),
            font = Color(0xff000000),
            icon = Color(0xff636363),
            card = Color(0xfff3f2f1),
            overlap = Color(0xffdad9d8),
        ),
        "charcoal" to NoteColor(
            base = Color(0xff767676),
            header = Color(0xff494745),
            content = Color(0xff696969),
            border = Color(0xff878787),
            font = Color(0xffffffff),
            icon = Color(0xffffffff),
            card = Color(0xff696969),
            overlap = Color(0xff878787),
        ),
    ),
    "dark" to mapOf(
        "yellow" to NoteColor(
            base = Color(0xffe17a20),
            header = Color(0xfffb923c),
            content = Color(0xff3a2b18),
            border = Color(0xfffb923c),
            font = Color(0xffececec),
            icon = Color(0xffffffff),
            card = Color(0xfffb923c),
            overlap = Color(0xff774600),
        ),
        "green" to NoteColor(
            base = Color(0xff459c4c),
            header = Color(0xff57965c),
            content = Color(0xff212a26),
            border = Color(0xff57965c),
            font = Color(0xffececec),
            icon = Color(0xffffffff),
            card = Color(0xff57965c),
            overlap = Color(0xff2d4c2d),
        ),
        "pink" to NoteColor(
            base = Color(0xffda3e3e),
            header = Color(0xffc94f4f),
            content = Color(0xff201517),
            border = Color(0xffc94f4f),
            font = Color(0xffececec),
            icon = Color(0xffffffff),
            card = Color(0xffc94f4f),
            overlap = Color(0xff6c2b2b),
        ),
        "purple" to NoteColor(
            base = Color(0xff6646ae),
            header = Color(0xff9470dc),
            content = Color(0xff220830),
            border = Color(0xff9470dc),
            font = Color(0xffececec),
            icon = Color(0xffffffff),
            card = Color(0xff9470dc),
            overlap = Color(0xff433361),
        ),
        "blue" to NoteColor(
            base = Color(0xff1b73e5),
            header = Color(0xff1b73e5),
            content = Color(0xff192d46),
            border = Color(0xff1b73e5),
            font = Color(0xffececec),
            icon = Color(0xffffffff),
            card = Color(0xff2b69d6),
            overlap = Color(0xff103770),
        ),
        "gray" to NoteColor(
            base = Color(0xffdddcdc),
            header = Color(0xffdddcdc),
            content = Color(0xff2b2d30),
            border = Color(0xffdad9d8),
            font = Color(0xffe1e1e1),
            icon = Color(0xff505050),
            card = Color(0xff2b2d30),
            overlap = Color(0xffdddcdc),
        ),
        "charcoal" to NoteColor(
            base = Color(0xff353538),
            header = Color(0xff3c3f41),
            content = Color(0xff1e1f22),
            border = Color(0xff3c3f41),
            font = Color(0xffececec),
            icon = Color(0xffffffff),
            card = Color(0xff3c3f41),
            overlap = Color(0xff1e1f22),
        ),
    ),
)

fun languages(): List<Pair<String, String>> {
    return listOf("zh" to "简体中文", "en" to "English")
}

fun colorStyles(colorTheme: String): List<String> {
    return (noteColorStyles[colorTheme] ?: emptyMap()).keys.toList()
}

private val defaultNoteColor = NoteColor(
    base = Color(0xff1b73e5),
    header = Color(0xff1b73e5),
    content = Color(0xff192d46),
    border = Color(0xff1b73e5),
    font = Color(0xffececec),
    icon = Color(0xffffffff),
    card = Color(0xff2b69d6),
    overlap = Color(0xff103770),
)

fun getNoteColor(colorTheme: String, style: String): NoteColor {
    try {
        val noteColorMap = noteColorStyles[colorTheme] ?: noteColorStyles["light"]!!
        return noteColorMap[style] ?: noteColorMap["green"]!!
    } catch (e: Exception) {
        log.error("getNoteColor error", e)
        return defaultNoteColor
    }
}