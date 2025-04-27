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
        emptyLineSelectionPaths.value = if (selection.length == 0) {
            emptyList()
        } else {
            val textLayout = textLayoutResult.value
            if (textLayout == null) {
                emptyList()
            } else {
                (0 until textLayout.lineCount).filter { lineIndex ->
                    val startOffset = textLayout.getLineStart(lineIndex)
                    val endOffset = textLayout.getLineEnd(lineIndex, false)
                    startOffset == endOffset && selection.contains(startOffset)
                }.map { lineIndex ->
                    val x0 = textLayout.getLineLeft(lineIndex)
                    val y0 = textLayout.getLineTop(lineIndex)
                    val y1 = textLayout.getLineBottom(lineIndex)
                    Path().apply {
                        moveTo(x0, y0)
                        lineTo(x0 + emptyLineWidth, y0)
                        lineTo(x0 + emptyLineWidth, y1)
                        lineTo(x0, y1)
                        close()
                    }
                }
            }
        }
    }
    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier.drawBehind {
            for (path in emptyLineSelectionPaths.value) {
                drawPath(
                    path = path,
                    brush = SolidColor(textSelectionColors.backgroundColor),
                    style = Fill
                )
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