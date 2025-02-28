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

fun SvgIcons.winRefresh(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinRefresh", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(733.0f, 379.1f)
            arcToRelative(
                264.1f, 264.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -468.1f,
                dy1 = 41.8f
            )
            arcToRelative(14.3f, 14.3f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -18.0f, dy1 = 8.2f)
            lineToRelative(-20.3f, -7.0f)
            arcToRelative(12.4f, 12.4f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -7.5f, dy1 = -16.2f)
            arcToRelative(
                312.1f, 312.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = true,
                dx1 = 556.7f,
                dy1 = -48.6f
            )
            lineToRelative(12.7f, -44.4f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 7.6f, dy1 = -9.6f)
            lineToRelative(24.8f, -13.7f)
            arcToRelative(14.5f, 14.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 20.9f, dy1 = 16.6f)
            lineToRelative(-38.1f, 133.0f)
            arcToRelative(11.1f, 11.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -13.8f, dy1 = 7.6f)
            lineToRelative(-133.0f, -38.1f)
            arcToRelative(14.5f, 14.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -3.0f, dy1 = -26.6f)
            lineToRelative(24.8f, -13.7f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 12.2f, dy1 = -1.4f)
            lineToRelative(42.0f, 12.0f)
            close()
            moveTo(285.5f, 659.5f)
            arcToRelative(
                264.1f, 264.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 468.1f,
                dy1 = -41.8f
            )
            arcToRelative(14.3f, 14.3f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 18.0f, dy1 = -8.2f)
            lineToRelative(20.3f, 7.0f)
            arcToRelative(12.4f, 12.4f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 7.4f, dy1 = 16.2f)
            curveToRelative(-46.4f, 118.0f, -160.8f, 199.1f, -290.4f, 199.1f)
            curveToRelative(-111.0f, 0.0f, -210.8f, -59.3f, -266.3f, -150.4f)
            lineToRelative(-12.7f, 44.3f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -7.6f, dy1 = 9.6f)
            lineToRelative(-24.8f, 13.7f)
            arcToRelative(14.5f, 14.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -20.9f, dy1 = -16.6f)
            lineToRelative(38.1f, -133.0f)
            arcToRelative(11.1f, 11.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 13.8f, dy1 = -7.6f)
            lineToRelative(133.0f, 38.1f)
            arcToRelative(14.5f, 14.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 3.0f, dy1 = 26.6f)
            lineToRelative(-24.8f, 13.7f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -12.2f, dy1 = 1.4f)
            lineToRelative(-42.0f, -12.0f)
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
        Image(imageVector = SvgIcons.winRefresh(), contentDescription = "")
    }
}
