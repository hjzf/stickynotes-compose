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

fun SvgIcons.winBack(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinBack", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(192.1f, 548.4f)
            lineTo(418.3f, 774.6f)
            arcToRelative(36.4f, 36.4f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -51.5f, dy1 = 51.5f)
            lineTo(82.6f, 541.8f)
            arcToRelative(42.2f, 42.2f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 0.0f, dy1 = -59.7f)
            lineToRelative(284.3f, -284.2f)
            arcToRelative(36.4f, 36.4f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 51.5f, dy1 = 51.5f)
            lineTo(192.1f, 475.6f)
            horizontalLineTo(919.8f)
            arcToRelative(36.4f, 36.4f, 0.0f, isMoreThanHalf = true, isPositiveArc = true, dx1 = 0.0f, dy1 = 72.8f)
            horizontalLineTo(192.1f)
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
        Image(imageVector = SvgIcons.winBack(), contentDescription = "")
    }
}
