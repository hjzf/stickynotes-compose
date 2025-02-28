package ui.windows

import LocalApplicationLocalization
import LocalProfileState
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import logic.DataStore
import logic.Note
import logic.colorStyles
import logic.getNoteColor
import ui.SvgIcons
import ui.components.ButtonWithIcon
import ui.icons.*
import ui.views.AnotherView
import ui.views.RawTextView
import kotlin.math.roundToInt

@Composable
private fun RowScope.TopBar(
    color: Color,
    rawTextMode: Boolean,
    onRawTextModeChange: (Boolean) -> Unit,
    alwaysOnTop: Boolean,
    tip: String,
    onIncreaseClick: () -> Unit,
    onMoreClick: () -> Unit,
    onPinClick: () -> Unit,
    onCloseClick: () -> Unit,
    onDownClick: () -> Unit,
) {
    val localProfileState = LocalProfileState.current
    val localApplicationLocalization = LocalApplicationLocalization.current
    ButtonWithIcon(
        text = localApplicationLocalization.newNote,
        iconSize = 24.dp,
        width = 32.dp,
        height = 32.dp,
        icon = SvgIcons.winIncrease(color),
        showTooltip = localProfileState.tooltip,
        onClick = onIncreaseClick
    )
    ButtonWithIcon(
        text = if (rawTextMode) {
            localApplicationLocalization.plainTextMode
        } else {
            localApplicationLocalization.imageTextMode
        },
        iconSize = if (rawTextMode) 19.dp else 20.dp,
        width = 32.dp,
        height = 32.dp,
        icon = if (rawTextMode) SvgIcons.winText(color) else SvgIcons.winImage(color),
        showTooltip = localProfileState.tooltip,
        onClick = { onRawTextModeChange(!rawTextMode) }
    )
    if (!rawTextMode) {
        ButtonWithIcon(
            text = localApplicationLocalization.injectFromClipboard,
            iconSize = 21.dp,
            width = 32.dp,
            height = 32.dp,
            icon = SvgIcons.winDown(color),
            showTooltip = localProfileState.tooltip,
            onClick = onDownClick
        )
    }
    Spacer(modifier = Modifier.weight(1f))
    if (tip != "") {
        ButtonWithText(
            text = tip,
            fontColor = color,
            onClick = {}
        )
    }
    ButtonWithIcon(
        text = localApplicationLocalization.menu,
        iconSize = 32.dp,
        width = 32.dp,
        height = 32.dp,
        icon = SvgIcons.winMore(color),
        showTooltip = localProfileState.tooltip,
        onClick = onMoreClick
    )
    ButtonWithIcon(
        text = if (alwaysOnTop) {
            localApplicationLocalization.unpin
        } else {
            localApplicationLocalization.pin
        },
        iconSize = 19.dp,
        width = 32.dp,
        height = 32.dp,
        icon = if (alwaysOnTop) SvgIcons.pinFill(color) else SvgIcons.pin(color),
        showTooltip = localProfileState.tooltip,
        onClick = onPinClick
    )
    ButtonWithIcon(
        text = localApplicationLocalization.closeNote,
        iconSize = 26.dp,
        width = 32.dp,
        height = 32.dp,
        icon = SvgIcons.winClose(color),
        showTooltip = localProfileState.tooltip,
        onClick = onCloseClick
    )
}

@Composable
private fun ButtonWithText(
    text: String,
    fontColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Box(
        modifier = modifier
            .hoverable(interactionSource = interactionSource, enabled = true)
            .clickable(onClick = onClick)
            .wrapContentWidth()
            .height(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            color = fontColor,
            modifier = Modifier.background(color = Color.Transparent)
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun NoteWindow(
    note: Note,
    onCloseButtonClick: () -> Unit,
    openMainWindow: () -> Unit,
) {
    val windowState = rememberWindowState(
        width = note.width.dp,
        height = note.height.dp,
        position = if (note.x < 0 || note.y < 0) {
            WindowPosition.Aligned(Alignment.Center)
        } else {
            WindowPosition(note.x.dp, note.y.dp)
        },
    )
    val alwaysOnTop = remember { mutableStateOf(note.alwaysOnTop) }
    val rawTextMode = remember { mutableStateOf(note.mode == 0) }
    val drawerVisible = remember { mutableStateOf(false) }
    val clipboardSignal = remember { mutableStateOf(false) }
    val position1 = remember { mutableStateOf(note.position1) }
    val position2 = remember { mutableStateOf(note.position2) }
    val position3 = remember { mutableStateOf(note.position3) }
    val tip = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val localProfileState = LocalProfileState.current
    val localApplicationLocalization = LocalApplicationLocalization.current
    val noteColor = remember(localProfileState.colorTheme, note.style) {
        getNoteColor(localProfileState.colorTheme, note.style)
    }
    DisposableEffect(Unit) {
        onDispose {
            DataStore.updateNoteWidthAndHeight(
                note.id,
                windowState.size.width.value.roundToInt(),
                windowState.size.height.value.roundToInt()
            )
            DataStore.updateNoteXAndY(
                note.id,
                windowState.position.x.value.roundToInt(),
                windowState.position.y.value.roundToInt()
            )
        }
    }
    Window(
        onCloseRequest = {
            coroutineScope.launch {
                DataStore.updateNoteVisible(note.id, false)
            }
            onCloseButtonClick()
        },
        title = localApplicationLocalization.stickynotes,
        icon = rememberVectorPainter(SvgIcons.Watermelon),
        transparent = true,
        undecorated = true,
        state = windowState,
        alwaysOnTop = alwaysOnTop.value,
    ) {
        WindowDraggableArea(
            modifier = Modifier.fillMaxWidth().height((32.5).dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxWidth().height((0.5).dp)
                        .background(noteColor.border)
                ) {}
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    Box(
                        modifier = Modifier.width((0.5).dp).fillMaxHeight()
                            .background(noteColor.border)
                    ) {}
                    Row(
                        modifier = Modifier.weight(1f).fillMaxHeight()
                            .background(
                                noteColor.header
                                    .copy(alpha = (localProfileState.backgroundAlpha.toFloat() / 255f))
                            )
                    ) {
                        TopBar(
                            noteColor.icon,
                            rawTextMode = rawTextMode.value,
                            onRawTextModeChange = {
                                rawTextMode.value = it
                                coroutineScope.launch {
                                    DataStore.updateNoteMode(
                                        note.id,
                                        if (rawTextMode.value) 0 else 1
                                    )
                                }
                            },
                            alwaysOnTop = alwaysOnTop.value,
                            tip = tip.value,
                            onIncreaseClick = {
                                coroutineScope.launch { DataStore.addNewNote() }
                            },
                            onMoreClick = { drawerVisible.value = true },
                            onPinClick = {
                                alwaysOnTop.value = !alwaysOnTop.value
                                coroutineScope.launch {
                                    DataStore.updateNoteAlwaysOnTop(
                                        note.id,
                                        alwaysOnTop.value
                                    )
                                }
                            },
                            onCloseClick = {
                                coroutineScope.launch {
                                    DataStore.updateNoteVisible(note.id, false)
                                }
                                onCloseButtonClick()
                            },
                            onDownClick = {
                                coroutineScope.launch { clipboardSignal.value = true }
                            }
                        )
                    }
                    Box(
                        modifier = Modifier.width((0.5).dp).fillMaxHeight()
                            .background(noteColor.border)
                    ) {}
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().height(windowState.size.height - (32.5).dp).offset(0.dp, (32.5).dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                Box(
                    modifier = Modifier.width((0.5).dp).fillMaxHeight()
                        .background(noteColor.border)
                ) {}
                Row(
                    modifier = Modifier.weight(1f).fillMaxHeight()
                        .background(
                            noteColor.content
                                .copy(alpha = (localProfileState.backgroundAlpha.toFloat() / 255f))
                        )
                ) {
                    if (rawTextMode.value) {
                        RawTextView(
                            note = note,
                            changeTip = { text: String, time: Long ->
                                tip.value = text
                                if (time > 0L) {
                                    coroutineScope.launch {
                                        delay(time)
                                        tip.value = ""
                                    }
                                }
                            },
                        )
                    } else {
                        AnotherView(
                            note = note,
                            changeTip = { text: String, time: Long ->
                                tip.value = text
                                if (time > 0L) {
                                    coroutineScope.launch {
                                        delay(time)
                                        tip.value = ""
                                    }
                                }
                            },
                            clipboardSignal = clipboardSignal.value,
                            onClipboardSignalChange = { clipboardSignal.value = it },
                            position1 = position1.value,
                            position2 = position2.value,
                            position3 = position3.value,
                            onPositionChange = { value1, value2, value3 ->
                                position1.value = value1
                                position2.value = value2
                                position3.value = value3
                            }
                        )
                    }
                }
                Box(
                    modifier = Modifier.width((0.5).dp).fillMaxHeight()
                        .background(noteColor.border)
                ) {}
            }
            Box(
                modifier = Modifier.fillMaxWidth().height((0.5).dp)
                    .background(noteColor.border)
            ) {}
        }
        AnimatedVisibility(
            visible = drawerVisible.value,
            enter = fadeIn(animationSpec = tween(200)),
            exit = fadeOut(animationSpec = tween(200)),
        ) {
            Column(
                modifier = Modifier.fillMaxSize().background(Color(0x33ffffff))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.Transparent)
                ) {}
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((windowState.size.height.value - 150).dp)
                        .background(Color.Transparent)
                        .clickable(
                            onClick = { drawerVisible.value = false },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        )
                ) {}
            }
        }
        AnimatedVisibility(
            visible = drawerVisible.value,
            enter = slideInVertically(animationSpec = tween(200), initialOffsetY = { -it }),
            exit = slideOutVertically(animationSpec = tween(200), targetOffsetY = { -it }),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().height(150.dp),
                elevation = 3.dp,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        colorStyles(localProfileState.colorTheme).forEach {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                                    .background(getNoteColor(localProfileState.colorTheme, it).base)
                                    .clickable {
                                        coroutineScope.launch {
                                            DataStore.updateNoteStyle(note.id, it)
                                        }
                                        drawerVisible.value = false
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (it == note.style) {
                                    Image(
                                        painter = rememberVectorPainter(
                                            SvgIcons.winCheck(
                                                getNoteColor(
                                                    localProfileState.colorTheme,
                                                    note.style
                                                ).font
                                            )
                                        ),
                                        contentDescription = "check",
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                    OptionRow(
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        onClick = {
                            openMainWindow()
                            drawerVisible.value = false
                        }
                    ) {
                        Box(modifier = Modifier.size(21.dp).offset(0.dp, 1.dp)) {
                            Image(
                                painter = rememberVectorPainter(SvgIcons.winList()),
                                contentDescription = localApplicationLocalization.notesList
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(modifier = Modifier.offset(0.dp, (-1).dp)) {
                            Text(text = localApplicationLocalization.notesList, fontSize = 16.sp, color = Color.Black)
                        }
                    }
                    OptionRow(
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        onClick = {
                            coroutineScope.launch { DataStore.exportMarkdown(note.id) }
                            drawerVisible.value = false
                        }
                    ) {
                        Box(modifier = Modifier.size(21.dp).offset(0.dp, 1.dp)) {
                            Image(
                                painter = rememberVectorPainter(SvgIcons.winExport()),
                                contentDescription = localApplicationLocalization.exportMarkdown
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(modifier = Modifier.offset(0.dp, (-1).dp)) {
                            Text(
                                text = localApplicationLocalization.exportMarkdown,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun OptionRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val hovered = interactionSource.collectIsHoveredAsState()
    Row(
        modifier = modifier
            .hoverable(interactionSource = interactionSource, enabled = true)
            .background(
                if (hovered.value) Color(0xFFEEEEEE) else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}