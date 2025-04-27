package ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.*
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    emptyLineWidth: Float = 5f,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Color.Black),
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit =
        @Composable { innerTextField -> innerTextField() },
    scrollState: TextFieldScrollState,
) {
    val textFieldValue = remember { mutableStateOf(TextFieldValue(value)) }
    CustomTextField(
        value = textFieldValue.value,
        onValueChange = {
            val string = textFieldValue.value.text
            textFieldValue.value = it
            if (it.text != string) {
                onValueChange(it.text)
            }
        },
        emptyLineWidth = emptyLineWidth,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        decorationBox = decorationBox,
        scrollState = scrollState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    emptyLineWidth: Float = 5f,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Color.Black),
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit =
        @Composable { innerTextField -> innerTextField() },
    scrollState: TextFieldScrollState,
) {
    val textSelectionColors = LocalTextSelectionColors.current
    val textLayoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }
    val emptyLineSelectionPaths = remember { mutableStateOf(emptyList<Path>()) }
    LaunchedEffect(value.selection, textLayoutResult.value) {
        val selection = value.selection
        val minIndex = selection.min
        val maxIndex = selection.max
        if (minIndex == maxIndex) {
            emptyLineSelectionPaths.value = emptyList()
            return@LaunchedEffect
        }
        val textLayout = textLayoutResult.value
        if (textLayout == null) {
            emptyLineSelectionPaths.value = emptyList()
            return@LaunchedEffect
        }
        val lineCount = textLayout.lineCount
        if (lineCount == 0) {
            emptyLineSelectionPaths.value = emptyList()
            return@LaunchedEffect
        }
        val list = ArrayList<Path>(lineCount)
        for (lineIndex in 0 until lineCount) {
            val x0 = textLayout.getLineLeft(lineIndex)
            val x1 = textLayout.getLineRight(lineIndex)
            val offset = textLayout.getLineStart(lineIndex)
            if (x0 == x1 && offset >= minIndex && offset <= maxIndex) {
                val y0 = textLayout.getLineTop(lineIndex)
                val y1 = textLayout.getLineBottom(lineIndex)
                list.add(Path().apply {
                    moveTo(x0, y0)
                    lineTo(x0 + emptyLineWidth, y0)
                    lineTo(x0 + emptyLineWidth, y1)
                    lineTo(x0, y1)
                    close()
                })
            }
        }
        emptyLineSelectionPaths.value = list
    }
    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier.drawBehind {
            val scrollOffset = scrollState.offset
            for (path in emptyLineSelectionPaths.value) {
                clipRect {
                    translate(top = -scrollOffset) {
                        drawPath(
                            path = path,
                            brush = SolidColor(textSelectionColors.backgroundColor),
                            style = Fill
                        )
                    }
                }
            }
        },
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        onTextLayout = {
            textLayoutResult.value = it
            onTextLayout(it)
        },
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        decorationBox = decorationBox,
        scrollState = scrollState
    )

}