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

fun SvgIcons.winDown(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinDown", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(864.0f, 884.0f)
            lineTo(160.0f, 884.0f)
            curveToRelative(-13.6f, 0.0f, -24.0f, -10.4f, -24.0f, -24.0f)
            reflectiveCurveToRelative(10.4f, -24.0f, 24.0f, -24.0f)
            horizontalLineToRelative(704.0f)
            curveToRelative(13.6f, 0.0f, 24.0f, 10.4f, 24.0f, 24.0f)
            reflectiveCurveToRelative(-10.4f, 24.0f, -24.0f, 24.0f)
            close()
            moveTo(512.0f, 787.2f)
            curveToRelative(-6.4f, 0.0f, -12.8f, -2.4f, -17.6f, -7.2f)
            lineToRelative(-293.6f, -304.0f)
            curveToRelative(-6.4f, -7.2f, -8.8f, -16.8f, -4.8f, -26.4f)
            curveToRelative(4.0f, -8.8f, 12.8f, -14.4f, 22.4f, -14.4f)
            horizontalLineToRelative(166.4f)
            lineTo(384.8f, 140.0f)
            curveToRelative(0.0f, -13.6f, 10.4f, -24.0f, 24.0f, -24.0f)
            horizontalLineToRelative(206.4f)
            curveToRelative(13.6f, 0.0f, 24.0f, 10.4f, 24.0f, 24.0f)
            verticalLineToRelative(295.2f)
            horizontalLineToRelative(166.4f)
            curveToRelative(9.6f, 0.0f, 18.4f, 5.6f, 22.4f, 14.4f)
            reflectiveCurveToRelative(1.6f, 19.2f, -4.8f, 26.4f)
            lineToRelative(-293.6f, 304.0f)
            curveToRelative(-4.8f, 4.8f, -11.2f, 7.2f, -17.6f, 7.2f)
            close()
            moveTo(275.2f, 483.2f)
            lineTo(512.0f, 728.8f)
            lineToRelative(236.8f, -245.6f)
            lineTo(615.2f, 483.2f)
            curveToRelative(-13.6f, 0.0f, -24.0f, -10.4f, -24.0f, -24.0f)
            lineTo(591.2f, 164.0f)
            lineTo(432.8f, 164.0f)
            verticalLineToRelative(295.2f)
            curveToRelative(0.0f, 13.6f, -10.4f, 24.0f, -24.0f, 24.0f)
            lineTo(275.2f, 483.2f)
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
        Image(imageVector = SvgIcons.winDown(), contentDescription = "")
    }
}
