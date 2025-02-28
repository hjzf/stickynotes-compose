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

fun SvgIcons.winEditImage(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinEditImage", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(46.5f, 977.5f)
            lineTo(46.5f, 46.5f)
            horizontalLineToRelative(930.9f)
            verticalLineToRelative(930.9f)
            close()
            moveTo(139.6f, 791.3f)
            verticalLineToRelative(93.1f)
            horizontalLineToRelative(744.7f)
            verticalLineToRelative(-166.4f)
            lineToRelative(-214.1f, -228.3f)
            close()
            moveTo(139.6f, 684.2f)
            lineTo(688.2f, 372.4f)
            lineTo(884.4f, 581.8f)
            lineTo(884.4f, 139.6f)
            lineTo(139.6f, 139.6f)
            close()
            moveTo(238.9f, 308.0f)
            arcToRelative(82.8f, 82.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 82.8f, dy1 = -82.8f)
            arcToRelative(82.8f, 82.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 82.8f, dy1 = 82.8f)
            arcToRelative(82.8f, 82.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -82.8f, dy1 = 82.8f)
            arcToRelative(82.8f, 82.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -82.8f, dy1 = -82.9f)
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
        Image(imageVector = SvgIcons.winEditImage(), contentDescription = "")
    }
}
