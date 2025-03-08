package ui.pages

import LocalApplicationLocalization
import LocalProfileState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.launch
import logic.DataStore
import logic.ProfileState
import logic.languages
import ui.components.*
import java.nio.file.Path

@Composable
fun ProfilePage(
    profileFilePath: Path,
    onProfileStateChange: (ProfileState) -> Unit,
    lazyListState: LazyListState = rememberLazyListState()
) {
    val localProfileState = LocalProfileState.current
    val localApplicationLocalization = LocalApplicationLocalization.current
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                LabelAndSelect(
                    label = localApplicationLocalization.language,
                    value = localProfileState.language,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateLanguage(it, profileFilePath))
                        }
                    },
                    valueList = languages()
                )
            }
            item {
                LabelAndSlider(
                    label = localApplicationLocalization.fontSize,
                    value = localProfileState.fontSize,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateFontSize(it, profileFilePath))
                        }
                    },
                    valueRange = 2..72,
                )
            }
            item {
                LabelAndSlider(
                    label = localApplicationLocalization.fontWeight,
                    value = localProfileState.fontWeight,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateFontWeight(it, profileFilePath))
                        }
                    },
                    valueRange = 100..900,
                )
            }
            item {
                LabelAndSelect(
                    label = localApplicationLocalization.fontFamily,
                    value = localProfileState.fontFamily,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateFontFamily(it, profileFilePath))
                        }
                    },
                    valueList = listOf(
                        FontFamily.Monospace.name to FontFamily.Monospace.name,
                        FontFamily.SansSerif.name to FontFamily.SansSerif.name,
                        FontFamily.Serif.name to FontFamily.Serif.name,
                        FontFamily.Cursive.name to FontFamily.Cursive.name,
                        "default" to "default",
                    )
                )
            }
            item {
                LabelAndSlider(
                    label = localApplicationLocalization.backgroundAlpha,
                    value = localProfileState.backgroundAlpha,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateBackgroundAlpha(it, profileFilePath))
                        }
                    },
                    valueRange = 0..255,
                )
            }
            item {
                LabelAndSlider(
                    label = localApplicationLocalization.imageCache,
                    value = localProfileState.imageCache,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateImageCache(it, profileFilePath))
                        }
                    },
                    valueRange = 16..1024,
                )
            }
            item {
                LabelAndSwitch(
                    label = localApplicationLocalization.copyWhenClick,
                    value = localProfileState.copyWhenClick,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateCopyWhenClick(it, profileFilePath))
                        }
                    }
                )
            }
            item {
                LabelAndSelect(
                    label = localApplicationLocalization.colorTheme,
                    value = localProfileState.colorTheme,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateColorTheme(it, profileFilePath))
                        }
                    },
                    valueList = listOf(
                        "light" to localApplicationLocalization.light,
                        "dark" to localApplicationLocalization.dark,
                    )
                )
            }
            item {
                LabelAndSwitch(
                    label = localApplicationLocalization.tooltip,
                    value = localProfileState.tooltip,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateTooltip(it, profileFilePath))
                        }
                    }
                )
            }
            item {
                LabelAndDirectoryPicker(
                    label = localApplicationLocalization.dataPath,
                    value = localProfileState.dataPath,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateDataPath(it, profileFilePath))
                        }
                    },
                )
            }
            item {
                LabelAndSwitch(
                    label = localApplicationLocalization.transition,
                    value = localProfileState.transition,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateTransition(it, profileFilePath))
                        }
                    }
                )
            }
            item {
                LabelAndDirectoryPicker(
                    label = localApplicationLocalization.markdown,
                    value = localProfileState.markdownPath,
                    onValueChange = {
                        coroutineScope.launch {
                            onProfileStateChange(DataStore.updateMarkdownPath(it, profileFilePath))
                        }
                    },
                )
            }
        }
        CustomVerticalScrollbar(rememberScrollbarAdapter(lazyListState))
    }
}