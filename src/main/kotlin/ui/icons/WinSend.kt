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

fun SvgIcons.winSend(color: Color = Color(0xFFFFFFFF)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinSend", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(Color(0xFF3389EF)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(512.0f, 512.0f)
            moveToRelative(-455.1f, 0.0f)
            arcToRelative(455.1f, 455.1f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 910.2f, dy1 = 0.0f)
            arcToRelative(455.1f, 455.1f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = -910.2f, dy1 = 0.0f)
            close()
        }
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(515.0f, 760.7f)
            arcToRelative(21.8f, 21.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -21.8f, dy1 = -21.8f)
            verticalLineTo(309.2f)
            curveToRelative(0.0f, -12.1f, 9.7f, -21.8f, 21.8f, -21.8f)
            curveToRelative(12.1f, 0.0f, 21.8f, 9.7f, 21.8f, 21.8f)
            verticalLineToRelative(429.3f)
            curveToRelative(0.0f, 12.1f, -9.7f, 22.2f, -21.8f, 22.2f)
            close()
        }
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(698.6f, 511.6f)
            curveToRelative(-5.7f, 0.0f, -11.1f, -2.0f, -15.4f, -6.4f)
            lineToRelative(-168.2f, -168.2f)
            lineToRelative(-168.2f, 168.2f)
            arcToRelative(22.1f, 22.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -30.9f, dy1 = 0.0f)
            arcToRelative(22.1f, 22.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 0.0f, dy1 = -30.9f)
            lineToRelative(183.6f, -183.6f)
            curveToRelative(4.0f, -4.0f, 9.7f, -6.4f, 15.4f, -6.4f)
            reflectiveCurveToRelative(11.4f, 2.3f, 15.4f, 6.4f)
            lineToRelative(183.6f, 183.6f)
            arcToRelative(22.1f, 22.1f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 0.0f, dy1 = 30.9f)
            curveToRelative(-4.4f, 4.4f, -10.1f, 6.4f, -15.4f, 6.4f)
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
        Image(imageVector = SvgIcons.winSend(), contentDescription = "")
    }
}
