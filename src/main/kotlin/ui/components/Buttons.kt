package ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonWithIcon(
    text: String,
    width: Dp,
    height: Dp,
    iconSize: Dp,
    icon: ImageVector,
    showTooltip: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    if (showTooltip) {
        TooltipArea(
            tooltip = {
                Surface(
                    modifier = Modifier.shadow(6.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(0.dp),
                ) {
                    Text(
                        text = text,
                        modifier = Modifier.wrapContentSize().padding(5.dp),
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        color = Color.Black,
                    )
                }
            },
            modifier = Modifier.wrapContentSize(),
            delayMillis = 800,
            tooltipPlacement = TooltipPlacement.CursorPoint(offset = DpOffset(0.dp, 24.dp)),
        ) {
            Box(
                modifier = modifier
                    .hoverable(interactionSource = interactionSource, enabled = true)
                    .clickable(onClick = onClick)
                    .width(width)
                    .height(height),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(iconSize),
                    painter = rememberVectorPainter(icon),
                    contentDescription = text
                )
            }
        }
    } else {
        Box(
            modifier = modifier
                .hoverable(interactionSource = interactionSource, enabled = true)
                .clickable(onClick = onClick)
                .width(width)
                .height(height),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(iconSize),
                painter = rememberVectorPainter(icon),
                contentDescription = text
            )
        }
    }
}