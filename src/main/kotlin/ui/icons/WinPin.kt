package ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ui.SvgIcons

fun SvgIcons.winPin(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinPin", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(648.7f, 130.8f)
            arcToRelative(73.1f, 73.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 22.7f, dy1 = 15.4f)
            lineToRelative(191.6f, 191.8f)
            arcToRelative(73.1f, 73.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -22.1f, dy1 = 118.6f)
            lineToRelative(-67.9f, 30.1f)
            lineToRelative(-127.3f, 127.5f)
            lineToRelative(-10.1f, 140.2f)
            arcToRelative(73.1f, 73.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -124.7f, dy1 = 46.4f)
            lineToRelative(-123.7f, -123.8f)
            lineToRelative(-210.7f, 211.7f)
            lineToRelative(-51.8f, -51.6f)
            lineToRelative(210.8f, -211.8f)
            lineToRelative(-127.9f, -128.0f)
            arcToRelative(73.1f, 73.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 46.3f, dy1 = -124.6f)
            lineToRelative(144.2f, -10.8f)
            lineToRelative(125.1f, -125.2f)
            lineToRelative(29.4f, -67.8f)
            arcToRelative(73.1f, 73.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 96.2f, dy1 = -38.0f)
            close()
            moveTo(619.6f, 197.9f)
            lineToRelative(-34.9f, 80.5f)
            lineToRelative(-154.1f, 154.3f)
            lineToRelative(-171.4f, 12.8f)
            lineToRelative(303.3f, 303.5f)
            lineToRelative(12.0f, -167.4f)
            lineToRelative(156.2f, -156.4f)
            lineToRelative(80.4f, -35.6f)
            lineToRelative(-191.6f, -191.7f)
            close()
        }
    }.build()
    cache[color] = imageVector
    return imageVector
}

private var cache = HashMap<Color, ImageVector>()

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SvgIcons.winPin(), contentDescription = "")
    }
}
