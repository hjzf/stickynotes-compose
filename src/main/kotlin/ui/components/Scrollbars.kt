package ui.components

import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.v2.ScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val log: Logger = LoggerFactory.getLogger("Scrollbars")

@Composable
fun BoxScope.CustomVerticalScrollbar(adapter: ScrollbarAdapter) {
    val hoverInteractionSource = remember { MutableInteractionSource() }
    val hovered = hoverInteractionSource.collectIsHoveredAsState()
    val scrollbarVisible = remember { mutableStateOf(false) }
    LaunchedEffect(hovered.value) {
        try {
            if (hovered.value) {
                scrollbarVisible.value = true
            } else {
                delay(2000)
                scrollbarVisible.value = false
            }
        } catch (ignore: CancellationException) {
        } catch (e: Exception) {
            log.error("CustomVerticalScrollbar error", e)
        }
    }
    Box(
        modifier = Modifier.Companion.align(Alignment.CenterEnd)
            .width(12.dp)
            .fillMaxHeight()
            .background(color = Color.Transparent)
            .hoverable(interactionSource = hoverInteractionSource, enabled = true),
    ) {
        VerticalScrollbar(
            adapter = adapter,
            modifier = Modifier.align(Alignment.CenterEnd)
                .fillMaxHeight()
                .background(color = Color.Transparent),
            style = ScrollbarStyle(
                minimalHeight = 10.dp,
                thickness = if (scrollbarVisible.value) 12.dp else 2.dp,
                shape = RoundedCornerShape(0.dp),
                hoverDurationMillis = 300,
                unhoverColor = Color(0xff8b8b8b),
                hoverColor = Color(0xff636363)
            )
        )
    }
}