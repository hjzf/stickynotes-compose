package ui.views

import LocalApplicationLocalization
import LocalProfileState
import androidx.compose.foundation.*
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragData
import androidx.compose.ui.draganddrop.dragData
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.isCtrlPressed
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import logic.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState
import tool.clearAllHtmlTags
import tool.currentTimeAsTimestamp
import tool.isImageName
import tool.unescapeHtml4
import ui.SvgIcons
import ui.components.CustomVerticalScrollbar
import ui.icons.CopyImage
import ui.icons.DeleteImage
import ui.icons.OpenImage
import ui.icons.noImage
import java.awt.Cursor
import java.awt.Desktop
import java.awt.Image
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO
import kotlin.math.roundToInt

private val log: Logger = LoggerFactory.getLogger("AnotherView")

@OptIn(ExperimentalComposeUiApi::class, FlowPreview::class)
@Composable
fun AnotherView(
    note: Note,
    changeTip: (String, Long) -> Unit,
    clipboardSignal: Boolean,
    onClipboardSignalChange: (Boolean) -> Unit,
    position1: Int,
    position2: Int,
    position3: Int,
    onPositionChange: (Int, Int, Int) -> Unit,
) {
    val localProfileState = LocalProfileState.current
    val localApplicationLocalization = LocalApplicationLocalization.current
    val blocks = remember { SnapshotStateList<Block>() }
    val maxBlockId = remember { mutableStateOf(0) }
    val lazyListState = rememberLazyListState(position1, position2)
    val inputHeight = remember { mutableStateOf(position3.dp) }
    LaunchedEffect(Unit) {
        try {
            for (block in DataStore.loadBlocks(note.id)) {
                blocks.add(block)
                if (block.id > maxBlockId.value) {
                    maxBlockId.value = block.id
                }
            }
        } catch (e: Exception) {
            log.error("Failed to load blocks", e)
        }
    }
    val version = remember { mutableStateOf(0L) }
    val state = rememberReorderableLazyListState(lazyListState) { from, to ->
        try {
            blocks.add(to.index, blocks.removeAt(from.index))
            version.value += 1
        } catch (e: Exception) {
            log.error("Failed to reorder blocks", e)
        }
    }
    LaunchedEffect(clipboardSignal) {
        if (clipboardSignal) {
            try {
                val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                if (clipboard.isDataFlavorAvailable(DataFlavor.imageFlavor)) {
                    val transferable = clipboard.getContents(null)
                    val image = transferable.getTransferData(DataFlavor.imageFlavor) as Image
                    val bufferedImage = BufferedImage(
                        image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB
                    )
                    val bGr = bufferedImage.createGraphics()
                    bGr.drawImage(image, 0, 0, null)
                    bGr.dispose()
                    val name = "image-${currentTimeAsTimestamp()}.png"
                    val absoluteFile = File(localProfileState.dataPath, "./${name}")
                    ImageIO.write(bufferedImage, "png", absoluteFile)
                    blocks.add(Block(BlockType.IMAGE, "./${name}", ++maxBlockId.value))
                    version.value += 1
                    if (blocks.isNotEmpty()) {
                        lazyListState.animateScrollToItem(blocks.lastIndex)
                    }
                } else if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                    val transferable = clipboard.getContents(null)
                    val string = transferable.getTransferData(DataFlavor.stringFlavor) as String
                    blocks.add(Block(BlockType.TEXT, string, ++maxBlockId.value))
                    version.value += 1
                    if (blocks.isNotEmpty()) {
                        lazyListState.animateScrollToItem(blocks.lastIndex)
                    }
                }
                delay(600)
                onClipboardSignalChange(false)
            } catch (ignore: CancellationException) {
            } catch (e: Exception) {
                log.error("Failed to get clipboard data", e)
            }
        }
    }
    val coroutineScope = rememberCoroutineScope()
    val selectedIndex = remember { mutableStateOf(-1) }
    LaunchedEffect(selectedIndex.value) {
        if (selectedIndex.value in blocks.indices) {
            delay(3 * 60 * 1000)
            selectedIndex.value = -1
        }
    }
    val copiedIndex = remember { mutableStateOf(-1) }
    LaunchedEffect(copiedIndex.value) {
        if (copiedIndex.value in blocks.indices) {
            delay(3000)
            copiedIndex.value = -1
        }
    }
    LaunchedEffect(Unit) {
        snapshotFlow { version.value }
            .distinctUntilChanged { oldValue, newValue ->
                oldValue == newValue
            }
            .filter {
                it > 0L
            }
            .onEach {
                changeTip(localApplicationLocalization.updating, 0L)
            }
            .debounce(500)
            .onEach {
                DataStore.updateNoteContent(note.id, blocks)
                changeTip(localApplicationLocalization.saved, 500L)
            }.launchIn(coroutineScope)
    }
    DisposableEffect(Unit) {
        onDispose {
            val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
            val firstVisibleItemScrollOffset = lazyListState.firstVisibleItemScrollOffset
            val inputHeightAsInt = inputHeight.value.value.roundToInt()
            onPositionChange(firstVisibleItemIndex, firstVisibleItemScrollOffset, inputHeightAsInt)
            DataStore.updateNotePosition(note.id, firstVisibleItemIndex, firstVisibleItemScrollOffset, inputHeightAsInt)
        }
    }
    val localClipboard = LocalClipboard.current
    val localWindowInfo = LocalWindowInfo.current
    val localDensity = LocalDensity.current
    val noteColor = remember(localProfileState.colorTheme, note.style) {
        getNoteColor(localProfileState.colorTheme, note.style)
    }
    val dragAndDropTarget = remember {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                try {
                    when (val dragData = event.dragData()) {
                        is DragData.Image -> {
                        }

                        is DragData.Text -> {
                            val text = dragData.readText().clearAllHtmlTags().unescapeHtml4()
                            blocks.add(Block(BlockType.TEXT, text, ++maxBlockId.value))
                            version.value += 1
                            coroutineScope.launch {
                                if (blocks.isNotEmpty()) {
                                    lazyListState.animateScrollToItem(blocks.lastIndex)
                                }
                            }
                        }

                        is DragData.FilesList -> {
                            dragData.readFiles().forEach { filePath ->
                                val rawFilePath = if (filePath.startsWith("file:/")) {
                                    filePath.substring(6)
                                } else {
                                    filePath
                                }
                                if (rawFilePath.isImageName()) {
                                    val name = rawFilePath.substringAfterLast("/")
                                    val absoluteFile = File(localProfileState.dataPath, name)
                                    File(rawFilePath).copyTo(absoluteFile, overwrite = true)
                                    blocks.add(Block(BlockType.IMAGE, "./${name}", ++maxBlockId.value))
                                } else {
                                    blocks.add(Block(BlockType.TEXT, rawFilePath, ++maxBlockId.value))
                                }
                                version.value += 1
                                coroutineScope.launch {
                                    if (blocks.isNotEmpty()) {
                                        lazyListState.animateScrollToItem(blocks.lastIndex)
                                    }
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    log.error("Failed to get data", e)
                }
                return true
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .dragAndDropTarget(shouldStartDragAndDrop = { true }, target = dragAndDropTarget)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize().clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { selectedIndex.value = -1 }),
            ) {
                itemsIndexed(
                    blocks,
                    key = { _, block -> block.id },
                    contentType = { _, block -> block.type },
                ) { index, block ->
                    ReorderableItem(
                        state = state,
                        key = block.id,
                        animateItemModifier = if (localProfileState.transition) {
                            Modifier.animateItem()
                        } else {
                            Modifier
                        },
                        enabled = when (block.type) {
                            BlockType.TEXT,
                            BlockType.BOLD,
                            BlockType.ITALIC,
                            BlockType.UNDERLINE,
                            BlockType.LINE_THROUGH -> {
                                selectedIndex.value != index
                            }

                            BlockType.IMAGE -> {
                                true
                            }
                        }
                    ) { _ ->
                        when (block.type) {
                            BlockType.TEXT, BlockType.BOLD, BlockType.ITALIC, BlockType.UNDERLINE, BlockType.LINE_THROUGH -> {
                                TextBlock(
                                    modifier = Modifier.padding(
                                        start = 12.dp,
                                        end = 12.dp,
                                        top = if (index == 0) 12.dp else 0.dp,
                                        bottom = 12.dp
                                    ).fillMaxWidth().wrapContentHeight().draggableHandle(
                                        onDragStarted = {
                                        },
                                        onDragStopped = {
                                        },
                                    ),
                                    block = block,
                                    profileState = localProfileState,
                                    localWindowInfo = localWindowInfo,
                                    note = note,
                                    editable = (selectedIndex.value == index),
                                    makeItEditable = {
                                        selectedIndex.value = index
                                        if (copiedIndex.value == index) {
                                            copiedIndex.value = -1
                                        }
                                    },
                                    copied = (copiedIndex.value == index),
                                    copyText = {
                                        if (selectedIndex.value != index && block.content.isNotEmpty()) {
                                            coroutineScope.launch {
                                                try {
                                                    localClipboard.setClipEntry(ClipEntry(StringSelection(block.content)))
                                                    copiedIndex.value = index
                                                } catch (e: Exception) {
                                                    log.error("Failed to copy text", e)
                                                }
                                            }
                                        }
                                    },
                                    deleteText = {
                                        blocks.removeAt(index)
                                        version.value += 1
                                    },
                                    onValueChange = {
                                        blocks[index] = block.copy(content = it)
                                        version.value += 1
                                    },
                                    onBlockTypeChange = {
                                        blocks[index] = block.copy(type = it)
                                        version.value += 1
                                    }
                                )
                            }

                            BlockType.IMAGE -> {
                                ImageBlock(
                                    modifier = Modifier.padding(
                                        start = 12.dp,
                                        end = 12.dp,
                                        top = if (index == 0) 12.dp else 0.dp,
                                        bottom = 12.dp
                                    ).fillMaxWidth().wrapContentHeight().draggableHandle(
                                        onDragStarted = {
                                        },
                                        onDragStopped = {
                                        },
                                    ),
                                    block = block,
                                    note = note,
                                    profileState = localProfileState,
                                    openImage = {
                                        val imagePath = block.content.trim()
                                        if (DataStore.loadImage(imagePath) != null) {
                                            try {
                                                val file = File(imagePath)
                                                val absoluteFile = if (file.isAbsolute) file else File(
                                                    localProfileState.dataPath, imagePath
                                                )
                                                if (absoluteFile.exists() && absoluteFile.isFile && absoluteFile.canRead()) {
                                                    Desktop.getDesktop().open(absoluteFile)
                                                } else {
                                                    coroutineScope.launch {
                                                        changeTip(
                                                            localApplicationLocalization.imageOpenFailed,
                                                            2000L
                                                        )
                                                    }
                                                }
                                            } catch (e: Exception) {
                                                log.error("Failed to open image", e)
                                                coroutineScope.launch {
                                                    changeTip(localApplicationLocalization.imageOpenFailed, 2000L)
                                                }
                                            }
                                        } else {
                                            coroutineScope.launch {
                                                changeTip(localApplicationLocalization.imageNotExist, 2000L)
                                            }
                                        }
                                    },
                                    copyImage = {
                                        val imagePath = block.content.trim()
                                        val targetImageBitmap = DataStore.loadImage(imagePath)
                                        if (targetImageBitmap != null) {
                                            try {
                                                val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                                                clipboard.setContents(
                                                    ImageSelection(targetImageBitmap.toAwtImage()),
                                                    null
                                                )
                                                coroutineScope.launch {
                                                    changeTip(
                                                        localApplicationLocalization.imageCopySucceeded,
                                                        2000L
                                                    )
                                                }
                                            } catch (e: Exception) {
                                                log.error("Failed to copy image", e)
                                                coroutineScope.launch {
                                                    changeTip(localApplicationLocalization.imageCopyFailed, 2000L)
                                                }
                                            }
                                        } else {
                                            coroutineScope.launch {
                                                changeTip(localApplicationLocalization.imageNotExist, 2000L)
                                            }
                                        }
                                    },
                                    deleteImage = {
                                        if (blocks.remove(block)) {
                                            version.value += 1
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
            CustomVerticalScrollbar(rememberScrollbarAdapter(lazyListState))
        }
        val draggableState = rememberDraggableState {
            with(localDensity) {
                inputHeight.value = (inputHeight.value.toPx() - it).coerceIn(10.dp.toPx(), 500.dp.toPx()).toDp()
            }
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Vertical
                )
                .background(color = noteColor.border)
                .pointerHoverIcon(icon = PointerIcon(Cursor(Cursor.N_RESIZE_CURSOR)))
        )
        Box(
            modifier = Modifier.fillMaxWidth().height(height = inputHeight.value)
        ) {
            Input(
                localWindowInfo, blocks, maxBlockId, version, coroutineScope, lazyListState, note, localProfileState
            )
        }
    }
}

@Composable
private fun BoxScope.Input(
    localWindowInfo: WindowInfo,
    blocks: SnapshotStateList<Block>,
    maxBlockId: MutableState<Int>,
    version: MutableState<Long>,
    coroutineScope: CoroutineScope,
    lazyListState: LazyListState,
    note: Note,
    profileState: ProfileState
) {
    val textFieldFocusRequester = remember { FocusRequester() }
    val textFieldState = remember { mutableStateOf(TextFieldValue("", TextRange.Zero)) }
    val blockTypeState = remember { mutableStateOf(BlockType.TEXT) }
    val localApplicationLocalization = LocalApplicationLocalization.current
    val noteColor = remember(profileState.colorTheme, note.style) {
        getNoteColor(profileState.colorTheme, note.style)
    }
    BasicTextField(
        value = textFieldState.value,
        onValueChange = {
            textFieldState.value = it.copy(text = it.text.replace("\t", " "))
        },
        modifier = Modifier.padding(horizontal = 22.dp, vertical = 6.dp).fillMaxSize()
            .focusRequester(textFieldFocusRequester).onKeyEvent {
                if (it.type == KeyEventType.KeyDown) {
                    when (it.key) {
                        Key.Enter -> {
                            if (localWindowInfo.keyboardModifiers.isCtrlPressed) {
                                if (textFieldState.value.text.isNotEmpty()) {
                                    blocks.add(
                                        Block(
                                            blockTypeState.value, textFieldState.value.text, ++maxBlockId.value
                                        )
                                    )
                                    version.value += 1
                                    textFieldState.value = TextFieldValue(text = "", selection = TextRange.Zero)
                                    coroutineScope.launch {
                                        if (blocks.isNotEmpty()) {
                                            lazyListState.animateScrollToItem(blocks.lastIndex)
                                        }
                                    }
                                }
                                true
                            } else {
                                false
                            }
                        }

                        Key.B -> {
                            if (localWindowInfo.keyboardModifiers.isCtrlPressed) {
                                blockTypeState.value = if (blockTypeState.value != BlockType.BOLD) {
                                    BlockType.BOLD
                                } else {
                                    BlockType.TEXT
                                }
                                true
                            } else {
                                false
                            }
                        }

                        Key.I -> {
                            if (localWindowInfo.keyboardModifiers.isCtrlPressed) {
                                blockTypeState.value = if (blockTypeState.value != BlockType.ITALIC) {
                                    BlockType.ITALIC
                                } else {
                                    BlockType.TEXT
                                }
                                true
                            } else {
                                false
                            }
                        }

                        Key.U -> {
                            if (localWindowInfo.keyboardModifiers.isCtrlPressed) {
                                blockTypeState.value = if (blockTypeState.value != BlockType.UNDERLINE) {
                                    BlockType.UNDERLINE
                                } else {
                                    BlockType.TEXT
                                }
                                true
                            } else {
                                false
                            }
                        }

                        Key.T -> {
                            if (localWindowInfo.keyboardModifiers.isCtrlPressed) {
                                blockTypeState.value = if (blockTypeState.value != BlockType.LINE_THROUGH) {
                                    BlockType.LINE_THROUGH
                                } else {
                                    BlockType.TEXT
                                }
                                true
                            } else {
                                false
                            }
                        }

                        else -> {
                            false
                        }
                    }
                } else {
                    false
                }
            },
        singleLine = false,
        cursorBrush = SolidColor(noteColor.font),
        textStyle = LocalTextStyle.current.copy(
            fontSize = profileState.fontSize.sp,
            lineHeight = (profileState.fontSize + 8).sp,
            fontFamily = when (profileState.fontFamily) {
                FontFamily.Serif.name -> FontFamily.Serif
                FontFamily.SansSerif.name -> FontFamily.SansSerif
                FontFamily.Monospace.name -> FontFamily.Monospace
                FontFamily.Cursive.name -> FontFamily.Cursive
                else -> FontFamily.Default
            },
            textDecoration = when (blockTypeState.value) {
                BlockType.UNDERLINE -> TextDecoration.Underline
                BlockType.LINE_THROUGH -> TextDecoration.LineThrough
                else -> TextDecoration.None
            },
            fontStyle = when (blockTypeState.value) {
                BlockType.ITALIC -> FontStyle.Italic
                else -> FontStyle.Normal
            },
            fontWeight = when (blockTypeState.value) {
                BlockType.BOLD -> FontWeight.Bold
                else -> FontWeight(profileState.fontWeight)
            },
            color = noteColor.font,
        ),
        decorationBox = { innerTextField ->
            if (textFieldState.value.text.isEmpty()) {
                Text(
                    text = "${localApplicationLocalization.pleaseInput}...",
                    fontSize = profileState.fontSize.sp,
                    lineHeight = (profileState.fontSize + 8).sp,
                    fontFamily = when (profileState.fontFamily) {
                        FontFamily.Serif.name -> FontFamily.Serif
                        FontFamily.SansSerif.name -> FontFamily.SansSerif
                        FontFamily.Monospace.name -> FontFamily.Monospace
                        FontFamily.Cursive.name -> FontFamily.Cursive
                        else -> FontFamily.Default
                    },
                    textDecoration = when (blockTypeState.value) {
                        BlockType.UNDERLINE -> TextDecoration.Underline
                        BlockType.LINE_THROUGH -> TextDecoration.LineThrough
                        else -> TextDecoration.None
                    },
                    fontStyle = when (blockTypeState.value) {
                        BlockType.ITALIC -> FontStyle.Italic
                        else -> FontStyle.Normal
                    },
                    fontWeight = when (blockTypeState.value) {
                        BlockType.BOLD -> FontWeight.Bold
                        else -> FontWeight(profileState.fontWeight)
                    },
                    color = Color(0xFFA0A0A0),
                )
            }
            innerTextField()
        })
    if (textFieldState.value.text.isNotEmpty()) {
        Box(
            modifier = Modifier.width(40.dp).height(40.dp).padding(bottom = 10.dp, end = 10.dp)
                .align(Alignment.BottomEnd).clickable {
                    if (textFieldState.value.text.isNotEmpty()) {
                        blocks.add(Block(blockTypeState.value, textFieldState.value.text, ++maxBlockId.value))
                        version.value += 1
                        textFieldState.value = TextFieldValue(text = "", selection = TextRange.Zero)
                        coroutineScope.launch {
                            if (blocks.isNotEmpty()) {
                                lazyListState.animateScrollToItem(blocks.lastIndex)
                            }
                        }
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Filled.Done, "done", tint = MaterialTheme.colors.primary)
        }
    }
}

@Composable
private fun TextBlock(
    modifier: Modifier,
    block: Block,
    profileState: ProfileState,
    localWindowInfo: WindowInfo,
    note: Note,
    editable: Boolean,
    makeItEditable: () -> Unit,
    copied: Boolean,
    copyText: () -> Unit,
    deleteText: () -> Unit,
    onValueChange: (String) -> Unit,
    onBlockTypeChange: (BlockType) -> Unit,
) {
    val hoverInteractionSource = remember { MutableInteractionSource() }
    val hovered = hoverInteractionSource.collectIsHoveredAsState()
    val confirmDialogVisible = remember { mutableStateOf(false) }
    val blockTypeInputVisible = remember { mutableStateOf(false) }
    val blockTypeDescription = remember(block) { mutableStateOf(block.type.description) }
    val localApplicationLocalization = LocalApplicationLocalization.current
    val noteColor = remember(profileState.colorTheme, note.style) {
        getNoteColor(profileState.colorTheme, note.style)
    }
    LaunchedEffect(blockTypeDescription.value) {
        val type = blockTypeDescription.value.trim().lowercase(Locale.getDefault())
        val blockType = when (type) {
            BlockType.TEXT.description -> BlockType.TEXT
            BlockType.BOLD.description -> BlockType.BOLD
            BlockType.ITALIC.description -> BlockType.ITALIC
            BlockType.UNDERLINE.description -> BlockType.UNDERLINE
            BlockType.LINE_THROUGH.description -> BlockType.LINE_THROUGH
            else -> BlockType.TEXT
        }
        onBlockTypeChange(blockType)
        blockTypeDescription.value = blockType.description
    }
    Card(
        modifier = modifier.hoverable(hoverInteractionSource, enabled = true),
        backgroundColor = Color.Transparent,
        border = if (editable) BorderStroke(1.dp, MaterialTheme.colors.primary) else null,
        elevation = if (hovered.value) 1.dp else 0.dp,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (blockTypeInputVisible.value) {
                BlockTypeInput(
                    initValue = blockTypeDescription.value,
                    onConfirm = {
                        blockTypeDescription.value = it
                        blockTypeInputVisible.value = false
                    },
                    buttonText = localApplicationLocalization.ok,
                    profileState = profileState,
                    noteColor = noteColor,
                )
            }
            Box(
                modifier = Modifier.fillMaxSize().combinedClickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        if (profileState.copyWhenClick) {
                            copyText()
                        }
                    },
                    onDoubleClick = { makeItEditable() },
                )
            ) {
                if (editable) {
                    BasicTextField(
                        value = block.content,
                        onValueChange = {
                            onValueChange(it.replace("\t", " "))
                        },
                        modifier = Modifier.padding(horizontal = 10.dp).fillMaxSize().onKeyEvent {
                            if (it.type == KeyEventType.KeyDown) {
                                when (it.key) {
                                    Key.B -> {
                                        if (localWindowInfo.keyboardModifiers.isCtrlPressed) {
                                            onBlockTypeChange(if (block.type != BlockType.BOLD) BlockType.BOLD else BlockType.TEXT)
                                            true
                                        } else {
                                            false
                                        }
                                    }

                                    Key.I -> {
                                        if (localWindowInfo.keyboardModifiers.isCtrlPressed) {
                                            onBlockTypeChange(if (block.type != BlockType.ITALIC) BlockType.ITALIC else BlockType.TEXT)
                                            true
                                        } else {
                                            false
                                        }
                                    }

                                    Key.U -> {
                                        if (localWindowInfo.keyboardModifiers.isCtrlPressed) {
                                            onBlockTypeChange(if (block.type != BlockType.UNDERLINE) BlockType.UNDERLINE else BlockType.TEXT)
                                            true
                                        } else {
                                            false
                                        }
                                    }

                                    Key.T -> {
                                        if (localWindowInfo.keyboardModifiers.isCtrlPressed) {
                                            onBlockTypeChange(if (block.type != BlockType.LINE_THROUGH) BlockType.LINE_THROUGH else BlockType.TEXT)
                                            true
                                        } else {
                                            false
                                        }
                                    }

                                    else -> {
                                        false
                                    }
                                }
                            } else {
                                false
                            }
                        },
                        singleLine = false,
                        cursorBrush = SolidColor(noteColor.font),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = profileState.fontSize.sp,
                            lineHeight = (profileState.fontSize + 8).sp,
                            fontFamily = when (profileState.fontFamily) {
                                FontFamily.Serif.name -> FontFamily.Serif
                                FontFamily.SansSerif.name -> FontFamily.SansSerif
                                FontFamily.Monospace.name -> FontFamily.Monospace
                                FontFamily.Cursive.name -> FontFamily.Cursive
                                else -> FontFamily.Default
                            },
                            textDecoration = when (block.type) {
                                BlockType.UNDERLINE -> TextDecoration.Underline
                                BlockType.LINE_THROUGH -> TextDecoration.LineThrough
                                else -> TextDecoration.None
                            },
                            fontStyle = when (block.type) {
                                BlockType.ITALIC -> FontStyle.Italic
                                else -> FontStyle.Normal
                            },
                            fontWeight = when (block.type) {
                                BlockType.BOLD -> FontWeight.Bold
                                else -> FontWeight(profileState.fontWeight)
                            },
                            color = noteColor.font,
                        ),
                    )
                } else {
                    ContextMenuArea(
                        items = {
                            listOf(
                                ContextMenuItem(localApplicationLocalization.copyText) {
                                    copyText()
                                },
                                ContextMenuItem(localApplicationLocalization.deleteText) {
                                    confirmDialogVisible.value = true
                                },
//                                ContextMenuItem(localApplicationLocalization.markAs) {
//                                    blockTypeInputVisible.value = true
//                                },
                            )
                        }
                    ) {
                        Text(
                            text = block.content,
                            modifier = Modifier.padding(horizontal = 10.dp).fillMaxSize(),
                            fontSize = profileState.fontSize.sp,
                            lineHeight = (profileState.fontSize + 8).sp,
                            fontFamily = when (profileState.fontFamily) {
                                FontFamily.Serif.name -> FontFamily.Serif
                                FontFamily.SansSerif.name -> FontFamily.SansSerif
                                FontFamily.Monospace.name -> FontFamily.Monospace
                                FontFamily.Cursive.name -> FontFamily.Cursive
                                else -> FontFamily.Default
                            },
                            textDecoration = when (block.type) {
                                BlockType.UNDERLINE -> TextDecoration.Underline
                                BlockType.LINE_THROUGH -> TextDecoration.LineThrough
                                else -> TextDecoration.None
                            },
                            fontStyle = when (block.type) {
                                BlockType.ITALIC -> FontStyle.Italic
                                else -> FontStyle.Normal
                            },
                            fontWeight = when (block.type) {
                                BlockType.BOLD -> FontWeight.Bold
                                else -> FontWeight(profileState.fontWeight)
                            },
                            color = noteColor.font,
                        )
                    }
                }
                if (hovered.value && block.content.isEmpty()) {
                    Box(
                        modifier = Modifier.wrapContentWidth().height((profileState.fontSize + 8).dp)
                            .align(Alignment.CenterEnd).clickable { deleteText() },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Filled.Close, "delete", tint = MaterialTheme.colors.error)
                    }
                }
                if (copied) {
                    Box(
                        modifier = Modifier.wrapContentWidth().height((profileState.fontSize + 8).dp)
                            .align(Alignment.CenterEnd),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = localApplicationLocalization.textCopied,
                            modifier = Modifier.padding(horizontal = 10.dp),
                            fontSize = profileState.fontSize.sp,
                            lineHeight = (profileState.fontSize + 8).sp,
                            fontFamily = when (profileState.fontFamily) {
                                FontFamily.Serif.name -> FontFamily.Serif
                                FontFamily.SansSerif.name -> FontFamily.SansSerif
                                FontFamily.Monospace.name -> FontFamily.Monospace
                                FontFamily.Cursive.name -> FontFamily.Cursive
                                else -> FontFamily.Default
                            },
                            fontWeight = FontWeight(profileState.fontWeight),
                            color = MaterialTheme.colors.secondary,
                        )
                    }
                }
                if (confirmDialogVisible.value) {
                    AlertDialog(
                        onDismissRequest = {
                            confirmDialogVisible.value = false
                        },
                        backgroundColor = Color.White,
                        title = {
                            Text(localApplicationLocalization.confirmDelete, color = Color.Black)
                        },
                        text = {
                            Text(localApplicationLocalization.confirmDeleteThisText, color = Color.Black)
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    confirmDialogVisible.value = false
                                    deleteText()
                                }
                            ) { Text(localApplicationLocalization.delete) }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    confirmDialogVisible.value = false
                                }
                            ) {
                                Text(localApplicationLocalization.cancel)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BlockTypeInput(
    initValue: String,
    onConfirm: (String) -> Unit,
    buttonText: String,
    profileState: ProfileState,
    noteColor: NoteColor,
) {
    val textFieldValue = remember {
        mutableStateOf(TextFieldValue(initValue, TextRange(initValue.length)))
    }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = textFieldValue.value,
            onValueChange = {
                textFieldValue.value = it
            },
            modifier = Modifier
                .width(100.dp)
                .height((profileState.fontSize + 16).dp)
                .border(1.dp, noteColor.border)
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .focusRequester(focusRequester)
                .onKeyEvent {
                    if (it.type == KeyEventType.KeyUp && it.key == Key.Enter) {
                        onConfirm(textFieldValue.value.text)
                    }
                    false
                },
            singleLine = true,
            cursorBrush = SolidColor(noteColor.font),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight(profileState.fontWeight),
                fontSize = profileState.fontSize.sp,
                lineHeight = (profileState.fontSize + 8).sp,
                fontFamily = when (profileState.fontFamily) {
                    FontFamily.Serif.name -> FontFamily.Serif
                    FontFamily.SansSerif.name -> FontFamily.SansSerif
                    FontFamily.Monospace.name -> FontFamily.Monospace
                    FontFamily.Cursive.name -> FontFamily.Cursive
                    else -> FontFamily.Default
                },
                color = noteColor.font,
            ),
            decorationBox = { innerTextField ->
                innerTextField()
            }
        )
        Box(
            modifier = Modifier.wrapContentWidth().height((profileState.fontSize + 16).dp)
                .background(color = noteColor.header, shape = RoundedCornerShape(0.dp))
                .clickable(onClick = { onConfirm(textFieldValue.value.text) })
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                buttonText,
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight(profileState.fontWeight),
                    fontSize = profileState.fontSize.sp,
                    lineHeight = (profileState.fontSize + 8).sp,
                    fontFamily = when (profileState.fontFamily) {
                        FontFamily.Serif.name -> FontFamily.Serif
                        FontFamily.SansSerif.name -> FontFamily.SansSerif
                        FontFamily.Monospace.name -> FontFamily.Monospace
                        FontFamily.Cursive.name -> FontFamily.Cursive
                        else -> FontFamily.Default
                    },
                    color = noteColor.icon,
                )
            )
        }
    }
}

@Composable
private fun ImageBlock(
    modifier: Modifier,
    block: Block,
    note: Note,
    profileState: ProfileState,
    openImage: () -> Unit,
    copyImage: () -> Unit,
    deleteImage: () -> Unit,
) {
    val hoverInteractionSource = remember { MutableInteractionSource() }
    val hovered = hoverInteractionSource.collectIsHoveredAsState()
    val imagePath = remember(block.content) { block.content.trim() }
    val imageBitmap = remember(imagePath) { DataStore.loadImage(imagePath) }
    val localApplicationLocalization = LocalApplicationLocalization.current
    val noteColor = remember(profileState.colorTheme, note.style) {
        getNoteColor(profileState.colorTheme, note.style)
    }
    Card(
        modifier = modifier.hoverable(hoverInteractionSource, enabled = true),
        backgroundColor = Color.Transparent,
        border = null,
        elevation = if (hovered.value) 1.dp else 0.dp,
    ) {
        Box(
            modifier = Modifier.wrapContentWidth().heightIn(min = 36.dp)
        ) {
            if (imageBitmap != null) {
                ContextMenuArea(
                    items = {
                        listOf(
                            ContextMenuItem(localApplicationLocalization.viewImage) {
                                openImage()
                            },
                            ContextMenuItem(localApplicationLocalization.copyImage) {
                                copyImage()
                            },
                            ContextMenuItem(localApplicationLocalization.deleteImage) {
                                deleteImage()
                            },
                        )
                    }
                ) {
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = imagePath,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit,
                    )
                }
            } else {
                Image(
                    painter = rememberVectorPainter(SvgIcons.noImage(noteColor.font)),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(36.dp),
                    contentScale = ContentScale.Fit,
                )
            }
            if (hovered.value && imageBitmap != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.weight(1.0f))
                    Image(
                        painter = rememberVectorPainter(SvgIcons.OpenImage),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp).clickable { openImage() },
                    )
                    Image(
                        painter = rememberVectorPainter(SvgIcons.CopyImage),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp).clickable { copyImage() },
                    )
                    Image(
                        painter = rememberVectorPainter(SvgIcons.DeleteImage),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp).clickable { deleteImage() },
                    )
                }
            }
        }
    }
}

internal class ImageSelection(private val image: Image) : Transferable {
    override fun getTransferDataFlavors(): Array<DataFlavor> {
        return arrayOf(DataFlavor.imageFlavor)
    }

    override fun isDataFlavorSupported(flavor: DataFlavor): Boolean {
        return DataFlavor.imageFlavor.equals(flavor)
    }

    @Throws(UnsupportedFlavorException::class, IOException::class)
    override fun getTransferData(flavor: DataFlavor): Any {
        if (!DataFlavor.imageFlavor.equals(flavor)) {
            throw UnsupportedFlavorException(flavor)
        }
        return image
    }
}