package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
inline fun TransparentBox(
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Unspecified,
    borderWidth: Dp = 1.dp,
    borderLeft: Boolean = false,
    borderRight: Boolean = false,
    borderTop: Boolean = false,
    borderBottom: Boolean = false,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    val density = LocalDensity.current
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .drawBehind {
                val offsetX = if (borderRight) 1f else 0f // TODO
                val offsetY = if (borderBottom) 1f else 0f // TODO
                val width = size.width - offsetX
                val height = size.height - offsetY
                val borderWidthPx = borderWidth.toPx()
                val halfBorderWidthPx = borderWidthPx * 0.5f
                if (borderLeft) {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = halfBorderWidthPx, y = 0f),
                        end = Offset(x = halfBorderWidthPx, y = height),
                        strokeWidth = borderWidthPx
                    )
                }
                if (borderRight) {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = width - halfBorderWidthPx, y = 0f),
                        end = Offset(x = width - halfBorderWidthPx, y = height),
                        strokeWidth = borderWidthPx
                    )
                }
                if (borderTop) {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = borderWidthPx, y = halfBorderWidthPx),
                        end = Offset(x = width - borderWidthPx, y = halfBorderWidthPx),
                        strokeWidth = borderWidthPx
                    )
                }
                if (borderBottom) {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = borderWidthPx, y = height - halfBorderWidthPx),
                        end = Offset(x = width - borderWidthPx, y = height - halfBorderWidthPx),
                        strokeWidth = borderWidthPx
                    )
                }
            }
            .padding(
                start = if (borderLeft) borderWidth else 0.dp,
                end = if (borderRight) borderWidth + (1f / density.density).dp else 0.dp, // TODO
                top = if (borderTop) borderWidth else 0.dp,
                bottom = if (borderBottom) borderWidth + (1f / density.density).dp else 0.dp, // TODO
            ),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        content = content
    )
}