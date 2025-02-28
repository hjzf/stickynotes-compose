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

fun SvgIcons.winImage(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinImage", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(896.0f, 128.0f)
            curveToRelative(35.3f, 0.0f, 64.0f, 28.7f, 64.0f, 64.0f)
            verticalLineToRelative(640.0f)
            curveToRelative(0.0f, 35.3f, -28.7f, 64.0f, -64.0f, 64.0f)
            lineTo(128.0f, 896.0f)
            curveToRelative(-35.3f, 0.0f, -64.0f, -28.7f, -64.0f, -64.0f)
            lineTo(64.0f, 192.0f)
            curveToRelative(0.0f, -35.3f, 28.7f, -64.0f, 64.0f, -64.0f)
            horizontalLineToRelative(768.0f)
            close()
            moveTo(227.7f, 673.0f)
            lineTo(112.0f, 788.7f)
            lineTo(112.0f, 832.0f)
            curveToRelative(0.0f, 8.8f, 7.2f, 16.0f, 16.0f, 16.0f)
            horizontalLineToRelative(331.3f)
            lineTo(284.3f, 673.0f)
            curveToRelative(-15.6f, -15.6f, -40.9f, -15.6f, -56.6f, 0.0f)
            close()
            moveTo(675.7f, 543.4f)
            lineToRelative(-226.6f, 226.6f)
            lineTo(527.2f, 848.0f)
            lineTo(896.0f, 848.0f)
            curveToRelative(8.8f, 0.0f, 16.0f, -7.2f, 16.0f, -16.0f)
            verticalLineToRelative(-108.9f)
            lineTo(732.3f, 543.4f)
            curveToRelative(-15.6f, -15.6f, -40.9f, -15.6f, -56.6f, 0.0f)
            close()
            moveTo(896.0f, 176.0f)
            lineTo(128.0f, 176.0f)
            curveToRelative(-8.8f, 0.0f, -16.0f, 7.2f, -16.0f, 16.0f)
            verticalLineToRelative(528.8f)
            lineToRelative(81.8f, -81.8f)
            curveToRelative(34.4f, -34.4f, 90.1f, -34.4f, 124.4f, 0.0f)
            lineToRelative(97.0f, 97.0f)
            lineToRelative(226.6f, -226.6f)
            curveToRelative(34.4f, -34.4f, 90.1f, -34.4f, 124.4f, 0.0f)
            lineTo(912.0f, 655.2f)
            lineTo(912.0f, 192.0f)
            curveToRelative(0.0f, -8.8f, -7.2f, -16.0f, -16.0f, -16.0f)
            close()
            moveTo(352.0f, 321.6f)
            curveToRelative(53.0f, 0.0f, 96.0f, 43.0f, 96.0f, 96.0f)
            reflectiveCurveToRelative(-43.0f, 96.0f, -96.0f, 96.0f)
            reflectiveCurveToRelative(-96.0f, -43.0f, -96.0f, -96.0f)
            reflectiveCurveToRelative(43.0f, -96.0f, 96.0f, -96.0f)
            close()
            moveTo(352.0f, 369.6f)
            curveToRelative(-26.5f, 0.0f, -48.0f, 21.5f, -48.0f, 48.0f)
            curveToRelative(0.0f, 26.5f, 21.5f, 48.0f, 48.0f, 48.0f)
            curveToRelative(26.5f, 0.0f, 48.0f, -21.5f, 48.0f, -48.0f)
            curveToRelative(0.0f, -26.5f, -21.5f, -48.0f, -48.0f, -48.0f)
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
        Image(imageVector = SvgIcons.winImage(), contentDescription = "")
    }
}
