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

fun SvgIcons.winRawText(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinRawText", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(661.6f, 118.0f)
            lineTo(249.1f, 118.0f)
            curveToRelative(-39.1f, 0.0f, -70.3f, 31.8f, -70.3f, 70.3f)
            verticalLineToRelative(647.4f)
            curveToRelative(0.0f, 39.1f, 31.8f, 70.3f, 70.3f, 70.3f)
            horizontalLineToRelative(525.8f)
            curveToRelative(38.6f, 0.0f, 70.3f, -31.8f, 70.3f, -70.3f)
            lineTo(845.2f, 301.6f)
            lineTo(661.6f, 118.0f)
            close()
            moveTo(674.3f, 192.7f)
            lineToRelative(96.2f, 96.2f)
            lineTo(684.6f, 288.9f)
            curveToRelative(-5.4f, 0.0f, -10.3f, -4.9f, -10.3f, -10.3f)
            lineTo(674.3f, 192.7f)
            close()
            moveTo(801.2f, 835.7f)
            arcToRelative(26.2f, 26.2f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -26.3f, dy1 = 26.4f)
            lineTo(249.1f, 862.0f)
            arcToRelative(26.2f, 26.2f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -26.3f, dy1 = -26.4f)
            lineTo(222.8f, 188.3f)
            arcToRelative(26.2f, 26.2f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 26.3f, dy1 = -26.3f)
            verticalLineToRelative(-0.5f)
            horizontalLineToRelative(381.3f)
            verticalLineToRelative(117.2f)
            curveToRelative(0.0f, 29.8f, 24.4f, 54.2f, 54.2f, 54.2f)
            horizontalLineToRelative(116.7f)
            verticalLineToRelative(502.8f)
            close()
        }
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(348.7f, 386.1f)
            horizontalLineToRelative(189.0f)
            arcToRelative(21.8f, 21.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 21.9f, dy1 = -21.9f)
            arcToRelative(21.8f, 21.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -21.9f, dy1 = -21.9f)
            horizontalLineTo(348.7f)
            arcToRelative(21.8f, 21.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -21.9f, dy1 = 21.9f)
            arcToRelative(21.8f, 21.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 21.9f, dy1 = 21.9f)
            close()
            moveTo(674.8f, 644.8f)
            horizontalLineTo(348.7f)
            curveToRelative(-12.2f, 0.0f, -21.9f, 9.7f, -21.9f, 21.9f)
            reflectiveCurveToRelative(9.7f, 21.9f, 21.9f, 21.9f)
            horizontalLineToRelative(326.2f)
            curveToRelative(12.2f, 0.0f, 21.9f, -9.7f, 21.9f, -21.9f)
            reflectiveCurveTo(687.1f, 644.8f, 674.8f, 644.8f)
            close()
            moveTo(348.7f, 495.9f)
            arcToRelative(21.8f, 21.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -21.9f, dy1 = 21.9f)
            arcToRelative(21.8f, 21.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 21.9f, dy1 = 21.9f)
            horizontalLineToRelative(326.2f)
            arcToRelative(21.8f, 21.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 21.9f, dy1 = -21.9f)
            arcToRelative(21.8f, 21.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -21.9f, dy1 = -21.9f)
            horizontalLineTo(348.7f)
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
        Image(imageVector = SvgIcons.winRawText(), contentDescription = "")
    }
}
