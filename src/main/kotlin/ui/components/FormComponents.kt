package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ui.SvgIcons
import ui.icons.Watermelon
import java.awt.Component
import java.awt.HeadlessException
import java.io.File
import javax.swing.JDialog
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView
import kotlin.math.roundToInt

@Composable
private fun Label(
    text: String, minWidth: Int = 128
) {
    Box(
        modifier = Modifier.widthIn(min = minWidth.dp), contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            fontFamily = FontFamily.Default,
        )
    }
}

@Composable
fun LabelAndSlider(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    valueRange: IntRange = 0..100,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Label(text = label, minWidth = 128)
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))
        Label(text = value.toString(), minWidth = 40)
        Slider(
            value = value * 1f,
            onValueChange = { onValueChange(it.roundToInt()) },
            valueRange = valueRange.first * 1f..valueRange.last * 1f
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LabelAndSelect(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    valueList: List<Pair<String, String>>,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        val expanded = remember { mutableStateOf(false) }
        Label(text = label, minWidth = 128)
        Spacer(modifier = Modifier.weight(1f))
        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = it },
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, lineHeight = 22.sp),
                value = valueList.find { it.first == value }?.second ?: "",
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                valueList.forEach {
                    val interactionSource = remember { MutableInteractionSource() }
                    val hovered = interactionSource.collectIsHoveredAsState()
                    DropdownMenuItem(
                        modifier = Modifier.hoverable(interactionSource = interactionSource, enabled = true).background(
                            if (hovered.value) MaterialTheme.colors.primary.copy(alpha = 0.1f) else Color.Transparent
                        ),
                        onClick = {
                            onValueChange(it.first)
                            expanded.value = false
                        },
                    ) {
                        Text(
                            text = it.second, color = Color.Black, fontSize = 14.sp, lineHeight = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
private fun DirectoryChooser(
    title: String,
    version: Long,
    value: File,
    onValueChange: (File) -> Unit,
) {
    val image = rememberVectorPainter(SvgIcons.Watermelon)
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    DisposableEffect(version) {
        val job = GlobalScope.launch(Dispatchers.IO) {
            val currentDirectory = if (value.exists() && value.isDirectory) {
                value
            } else {
                File(System.getProperty("user.dir"))
            }
            val chooser = object : JFileChooser(currentDirectory, FileSystemView.getFileSystemView()) {
                @Throws(HeadlessException::class)
                override fun createDialog(parent: Component?): JDialog {
                    val dialog = super.createDialog(parent)
                    dialog.setIconImage(
                        image.toAwtImage(density = density, layoutDirection = layoutDirection)
                    )
                    return dialog
                }
            }
            chooser.dialogTitle = title
            chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
            chooser.isAcceptAllFileFilterUsed = false
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                val file = chooser.selectedFile
                if (file != null && file.exists() && file.isDirectory && file.canRead() && file.canWrite()) {
                    onValueChange(file)
                }
            }
        }
        onDispose {
            job.cancel()
        }
    }
}

@Composable
fun LabelAndDirectoryPicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val showDirectoryChooser = remember { mutableStateOf(false) }
    val version = remember { mutableStateOf(0L) }
    if (showDirectoryChooser.value) {
        DirectoryChooser(title = label, version = version.value, value = File(value), onValueChange = {
            onValueChange(it.absolutePath)
            showDirectoryChooser.value = false
        })
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Label(text = label, minWidth = 128)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable {
                showDirectoryChooser.value = true
                version.value = System.currentTimeMillis()
            }.padding(all = 16.dp),
            style = LocalTextStyle.current.copy(fontSize = 14.sp, lineHeight = 14.sp),
            text = value,
        )
    }
}

@Composable
fun LabelAndSwitch(
    label: String,
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Label(text = label, minWidth = 128)
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))
        Switch(
            checked = value,
            onCheckedChange = onValueChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary,
                checkedTrackColor = MaterialTheme.colors.primaryVariant,
                checkedTrackAlpha = 1f,
            )
        )
    }
}