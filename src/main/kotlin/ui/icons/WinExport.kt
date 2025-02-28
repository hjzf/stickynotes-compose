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

fun SvgIcons.winExport(color: Color = Color(0xFF000000)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinExport", defaultWidth = 256.0.dp, defaultHeight = 256.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(889.6f, 104.7f)
            curveToRelative(-7.6f, 0.0f, -15.2f, 2.9f, -21.0f, 8.7f)
            lineTo(401.8f, 580.1f)
            curveToRelative(-11.6f, 11.6f, -11.6f, 30.4f, 0.0f, 42.0f)
            curveToRelative(5.8f, 5.8f, 13.4f, 8.7f, 21.0f, 8.7f)
            reflectiveCurveToRelative(15.2f, -2.9f, 21.0f, -8.7f)
            lineToRelative(466.7f, -466.7f)
            curveToRelative(11.6f, -11.6f, 11.6f, -30.4f, 0.0f, -42.0f)
            curveToRelative(-5.7f, -5.8f, -13.3f, -8.7f, -20.9f, -8.7f)
            close()
        }
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(868.6f, 868.6f)
            horizontalLineTo(155.4f)
            verticalLineTo(155.4f)
            horizontalLineToRelative(267.4f)
            curveToRelative(7.6f, 0.0f, 15.2f, -2.9f, 21.0f, -8.7f)
            curveToRelative(5.8f, -5.8f, 8.7f, -13.4f, 8.7f, -21.0f)
            reflectiveCurveToRelative(-2.9f, -15.2f, -8.7f, -21.0f)
            curveToRelative(-5.8f, -5.8f, -13.4f, -8.7f, -21.0f, -8.7f)
            horizontalLineTo(155.4f)
            curveTo(122.6f, 96.0f, 96.0f, 122.6f, 96.0f, 155.4f)
            verticalLineToRelative(713.1f)
            curveToRelative(0.0f, 32.8f, 26.6f, 59.4f, 59.4f, 59.4f)
            horizontalLineToRelative(713.1f)
            curveToRelative(32.8f, 0.0f, 59.4f, -26.6f, 59.4f, -59.4f)
            verticalLineTo(601.1f)
            curveToRelative(0.0f, -7.6f, -2.9f, -15.2f, -8.7f, -21.0f)
            curveToRelative(-5.8f, -5.8f, -13.4f, -8.7f, -21.0f, -8.7f)
            reflectiveCurveToRelative(-15.2f, 2.9f, -21.0f, 8.7f)
            curveToRelative(-5.8f, 5.8f, -8.7f, 13.4f, -8.7f, 21.0f)
            verticalLineToRelative(267.5f)
            close()
            moveTo(868.6f, 96.0f)
            horizontalLineToRelative(-208.0f)
            curveToRelative(-7.6f, 0.0f, -15.2f, 2.9f, -21.0f, 8.7f)
            curveToRelative(-5.8f, 5.8f, -8.7f, 13.4f, -8.7f, 21.0f)
            reflectiveCurveToRelative(2.9f, 15.2f, 8.7f, 21.0f)
            curveToRelative(5.8f, 5.8f, 13.4f, 8.7f, 21.0f, 8.7f)
            horizontalLineToRelative(208.0f)
            verticalLineToRelative(208.0f)
            curveToRelative(0.0f, 7.6f, 2.9f, 15.2f, 8.7f, 21.0f)
            curveToRelative(5.8f, 5.8f, 13.4f, 8.7f, 21.0f, 8.7f)
            reflectiveCurveToRelative(15.2f, -2.9f, 21.0f, -8.7f)
            curveToRelative(5.8f, -5.8f, 8.7f, -13.4f, 8.7f, -21.0f)
            verticalLineToRelative(-208.0f)
            curveToRelative(0.0f, -32.8f, -26.6f, -59.4f, -59.4f, -59.4f)
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
        Image(imageVector = SvgIcons.winExport(), contentDescription = "")
    }
}
