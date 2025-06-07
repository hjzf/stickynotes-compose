import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalLocalization
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.application
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import logic.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ui.SvgIcons
import ui.icons.Watermelon
import ui.windows.MainWindow
import ui.windows.NoteWindow
import java.util.*
import javax.swing.UIManager
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createParentDirectories
import kotlin.io.path.div

private val log: Logger = LoggerFactory.getLogger("Main")
val LocalProfileState = compositionLocalOf { ProfileState() }
val LocalApplicationLocalization = compositionLocalOf { ApplicationLocalization() }

@FlowPreview
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    } catch (e: Exception) {
        log.error("Failed to set LookAndFeel", e)
    }
    try {
        val userDirPath = Path(System.getProperty("user.dir"))
        var profileFilePath = userDirPath / "conf" / "profile.txt"
        var defaultDataPath = userDirPath / "data"
        val osName = System.getProperty("os.name")?.lowercase(Locale.getDefault()) ?: ""
        if (osName.contains("win")) {
            val appData = System.getenv("APPDATA")
            if (!appData.isNullOrBlank()) {
                val appDataPath = Path(appData)
                profileFilePath = appDataPath / APP_NAME / "conf" / "profile.txt"
                defaultDataPath = appDataPath / APP_NAME / "data"
            }
        }
        profileFilePath.createParentDirectories()
        defaultDataPath.createDirectories()
        application {
            val profileState = remember { mutableStateOf(DataStore.loadProfileState(profileFilePath, defaultDataPath)) }
            val textFieldLocalization = remember(profileState.value.language) {
                mutableStateOf(
                    textFieldLocalizationMap[profileState.value.language] ?: defaultTextFieldLocalization
                )
            }
            val applicationLocalization = remember(profileState.value.language) {
                mutableStateOf(
                    applicationLocalizationMap[profileState.value.language] ?: defaultApplicationLocalization
                )
            }
            val notes = remember { mutableStateOf(DataStore.loadNotes()) }
            LaunchedEffect(Unit) {
                DataStore.windowStateVersionFlow.collect {
                    notes.value = DataStore.loadNotes()
                }
            }
            LaunchedEffect(Unit) {
                DataStore.dataPathVersionFlow.collect {
                    notes.value = emptyList()
                    delay(1000)
                    notes.value = DataStore.loadNotes()
                }
            }
            val themeColors = remember { mutableStateOf(defaultThemeColors) }
            val themeShapes = remember { mutableStateOf(defaultThemeShapes) }
            val mainWindowVisible = remember { mutableStateOf(true) }
            val mainWindowAlwaysOnTop = remember { mutableStateOf(false) }
            val trayVisible = remember { mutableStateOf(false) }
            LaunchedEffect(mainWindowVisible.value, trayVisible.value, notes.value) {
                if (!mainWindowVisible.value && !trayVisible.value && notes.value.none { it.visible }) {
                    exitApplication()
                }
            }
            CompositionLocalProvider(
                LocalLocalization provides textFieldLocalization.value,
                LocalProfileState provides profileState.value,
                LocalApplicationLocalization provides applicationLocalization.value,
            ) {
                MaterialTheme(colors = themeColors.value, shapes = themeShapes.value) {
                    if (trayVisible.value) {
                        Tray(
                            icon = rememberVectorPainter(SvgIcons.Watermelon),
                            tooltip = applicationLocalization.value.stickynotes,
                            onAction = {
                                trayVisible.value = !trayVisible.value
                            },
                            menu = {
                                Item("Show App", onClick = {
                                    trayVisible.value = !trayVisible.value
                                })
                                Item("Exit", onClick = ::exitApplication)
                            }
                        )
                    }
                    MainWindow(
                        visible = mainWindowVisible.value && !trayVisible.value,
                        alwaysOnTop = mainWindowAlwaysOnTop.value,
                        onCloseButtonClick = {
                            mainWindowVisible.value = false
                        },
                        exitApplication = {
                            exitApplication()
                        },
                        profileFilePath = profileFilePath,
                        onProfileStateChange = { profileState.value = it },
                    )
                    val coroutineScope = rememberCoroutineScope()
                    notes.value.forEach { note ->
                        key(note.id) {
                            if (note.visible) {
                                NoteWindow(
                                    note = note,
                                    visible = !trayVisible.value,
                                    minimizeToTray = {
                                        trayVisible.value = true
                                    },
                                    openMainWindow = {
                                        mainWindowVisible.value = true
                                        coroutineScope.launch {
                                            mainWindowAlwaysOnTop.value = true
                                            delay(1000)
                                            mainWindowAlwaysOnTop.value = false
                                        }
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    } catch (e: Exception) {
        log.error("Failed to start application", e)
    }
}
