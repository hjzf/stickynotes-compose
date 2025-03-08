package ui.windows

import LocalApplicationLocalization
import LocalProfileState
import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch
import logic.DataStore
import logic.ProfileState
import ui.SvgIcons
import ui.components.ButtonWithIcon
import ui.icons.*
import ui.pages.NoteListPage
import ui.pages.ProfilePage
import java.nio.file.Path

@Composable
private fun RowScope.NoteListPageTopBar(
    onIncreaseClick: () -> Unit,
    onProfileClick: () -> Unit,
    onCloseClick: () -> Unit,
    exitApplication: () -> Unit,
) {
    val localProfileState = LocalProfileState.current
    val localApplicationLocalization = LocalApplicationLocalization.current
    ButtonWithIcon(
        text = localApplicationLocalization.newNote,
        iconSize = 24.dp,
        width = 40.dp,
        height = 40.dp,
        icon = SvgIcons.winIncrease(),
        showTooltip = localProfileState.tooltip,
        onClick = onIncreaseClick
    )
    Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
        ContextMenuArea(
            items = {
                listOf(
                    ContextMenuItem(
                        localApplicationLocalization.closeWindow,
                        onCloseClick
                    ),
                    ContextMenuItem(
                        localApplicationLocalization.exitApplication,
                        exitApplication
                    )
                )
            }
        ) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
    ButtonWithIcon(
        text = localApplicationLocalization.settings,
        iconSize = 20.dp,
        width = 40.dp,
        height = 40.dp,
        icon = SvgIcons.winSetting(),
        showTooltip = localProfileState.tooltip,
        onClick = onProfileClick
    )
    ButtonWithIcon(
        text = localApplicationLocalization.closeWindow,
        iconSize = 26.dp,
        width = 40.dp,
        height = 40.dp,
        icon = SvgIcons.winClose(),
        showTooltip = localProfileState.tooltip,
        onClick = onCloseClick
    )
}

@Composable
private fun RowScope.ProfilePageTopBar(
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    val localProfileState = LocalProfileState.current
    val localApplicationLocalization = LocalApplicationLocalization.current
    ButtonWithIcon(
        text = localApplicationLocalization.back,
        iconSize = 20.dp,
        width = 40.dp,
        height = 40.dp,
        icon = SvgIcons.winBack(),
        showTooltip = localProfileState.tooltip,
        onClick = onBackClick
    )
    Spacer(modifier = Modifier.width(20.dp))
    Box(
        modifier = Modifier.height(40.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = localApplicationLocalization.settings,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            color = Color(0xFF636363)
        )
    }
    Spacer(modifier = Modifier.weight(1f))
    ButtonWithIcon(
        text = localApplicationLocalization.closeWindow,
        iconSize = 26.dp,
        width = 40.dp,
        height = 40.dp,
        icon = SvgIcons.winClose(),
        showTooltip = localProfileState.tooltip,
        onClick = onCloseClick
    )
}

private enum class Page {
    NoteList, Profile
}

@ExperimentalComposeUiApi
@Composable
fun MainWindow(
    visible: Boolean,
    alwaysOnTop: Boolean,
    onCloseButtonClick: () -> Unit,
    exitApplication: () -> Unit,
    profileFilePath: Path,
    onProfileStateChange: (ProfileState) -> Unit,
) {
    val windowState = rememberWindowState(
        width = 320.dp, height = 640.dp, position = WindowPosition.Aligned(Alignment.Center)
    )
    val currentPage = remember { mutableStateOf(Page.NoteList) }
    val coroutineScope = rememberCoroutineScope()
    val localApplicationLocalization = LocalApplicationLocalization.current
    Window(
        onCloseRequest = onCloseButtonClick,
        title = localApplicationLocalization.stickynotes,
        icon = rememberVectorPainter(SvgIcons.Watermelon),
        transparent = true,
        undecorated = true,
        state = windowState,
        alwaysOnTop = alwaysOnTop,
        visible = visible,
    ) {
        WindowDraggableArea(
            modifier = Modifier.fillMaxWidth().height((40.5).dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxWidth().height((0.5).dp).background(Color.LightGray)) {}
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    Box(modifier = Modifier.width((0.5).dp).fillMaxHeight().background(Color.LightGray)) {}
                    Row(modifier = Modifier.weight(1f).fillMaxHeight().background(Color.White)) {
                        when (currentPage.value) {
                            Page.NoteList -> {
                                NoteListPageTopBar(
                                    onIncreaseClick = { coroutineScope.launch { DataStore.addNewNote() } },
                                    onProfileClick = { currentPage.value = Page.Profile },
                                    onCloseClick = { onCloseButtonClick() },
                                    exitApplication = { exitApplication() }
                                )
                            }

                            Page.Profile -> {
                                ProfilePageTopBar(
                                    onBackClick = { currentPage.value = Page.NoteList },
                                    onCloseClick = { onCloseButtonClick() }
                                )
                            }
                        }
                    }
                    Box(modifier = Modifier.width((0.5).dp).fillMaxHeight().background(Color.LightGray)) {}
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().height((windowState.size.height - (40.5).dp)).offset(0.dp, (40.5).dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                Box(modifier = Modifier.width((0.5).dp).fillMaxHeight().background(Color.LightGray)) {}
                Row(modifier = Modifier.weight(1f).fillMaxHeight().background(Color.White)) {
                    when (currentPage.value) {
                        Page.NoteList -> {
                            NoteListPage()
                        }

                        Page.Profile -> {
                            ProfilePage(
                                profileFilePath = profileFilePath,
                                onProfileStateChange = onProfileStateChange,
                            )
                        }
                    }
                }
                Box(modifier = Modifier.width((0.5).dp).fillMaxHeight().background(Color.LightGray)) {}
            }
            Box(modifier = Modifier.fillMaxWidth().height((0.5).dp).background(Color.LightGray)) {}
        }
    }
}