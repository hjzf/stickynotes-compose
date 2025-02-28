package ui.icons

import androidx.compose.desktop.ui.tooling.preview.Preview
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
import androidx.compose.ui.unit.dp
import ui.SvgIcons

fun SvgIcons.pinFill(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "PinFill", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(912.9f, 400.9f)
            lineToRelative(-297.0f, -297.0f)
            arcToRelative(32.0f, 32.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -53.7f, dy1 = 14.9f)
            lineToRelative(-29.0f, 115.8f)
            lineToRelative(-128.0f, 128.0f)
            lineToRelative(-208.0f, 16.0f)
            curveToRelative(-27.3f, 2.1f, -39.5f, 35.2f, -20.2f, 54.5f)
            lineToRelative(186.1f, 186.1f)
            lineToRelative(-213.3f, 213.3f)
            lineToRelative(41.5f, 41.5f)
            lineToRelative(213.3f, -213.3f)
            lineToRelative(164.7f, 164.7f)
            curveToRelative(19.3f, 19.3f, 52.4f, 7.1f, 54.5f, -20.2f)
            lineToRelative(16.0f, -208.0f)
            lineToRelative(128.0f, -128.0f)
            lineToRelative(125.8f, -14.0f)
            curveToRelative(26.7f, -3.0f, 38.1f, -35.5f, 19.1f, -54.4f)
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
        Image(imageVector = SvgIcons.pinFill(), contentDescription = "")
    }
}
