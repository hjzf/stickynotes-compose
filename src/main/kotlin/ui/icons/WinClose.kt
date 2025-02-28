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

fun SvgIcons.winClose(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinClose", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(507.2f, 473.2f)
            lineTo(716.5f, 263.9f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 22.6f, dy1 = 0.0f)
            lineToRelative(11.3f, 11.3f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 0.0f, dy1 = 22.6f)
            lineTo(541.1f, 507.2f)
            lineTo(750.4f, 716.5f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 0.0f, dy1 = 22.6f)
            lineToRelative(-11.3f, 11.3f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -22.6f, dy1 = 0.0f)
            lineTo(507.2f, 541.1f)
            lineTo(297.9f, 750.4f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -22.6f, dy1 = 0.0f)
            lineToRelative(-11.3f, -11.3f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 0.0f, dy1 = -22.6f)
            lineToRelative(209.3f, -209.3f)
            lineToRelative(-209.3f, -209.3f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 0.0f, dy1 = -22.6f)
            lineToRelative(11.3f, -11.3f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 22.6f, dy1 = 0.0f)
            lineToRelative(209.3f, 209.3f)
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
        Image(imageVector = SvgIcons.winClose(), contentDescription = "")
    }
}
