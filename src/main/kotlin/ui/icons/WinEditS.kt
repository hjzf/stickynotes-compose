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

fun SvgIcons.winEditS(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinEditS", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(1024.0f, 511.8f)
            horizontalLineTo(687.1f)
            curveToRelative(-38.5f, -16.4f, -94.0f, -35.5f, -167.4f, -57.4f)
            curveToRelative(-77.1f, -22.3f, -126.3f, -39.1f, -146.4f, -50.3f)
            curveToRelative(-45.8f, -24.6f, -68.1f, -57.0f, -68.1f, -97.2f)
            curveToRelative(0.0f, -45.8f, 19.0f, -79.3f, 57.0f, -101.7f)
            curveToRelative(33.5f, -20.1f, 79.3f, -29.1f, 138.5f, -29.1f)
            curveToRelative(64.8f, 0.0f, 115.1f, 13.4f, 150.8f, 42.5f)
            curveToRelative(34.6f, 27.9f, 57.0f, 70.4f, 67.1f, 128.5f)
            horizontalLineTo(809.0f)
            curveToRelative(-7.8f, -83.8f, -38.0f, -147.4f, -91.6f, -189.9f)
            curveTo(666.0f, 115.9f, 594.5f, 95.8f, 505.1f, 95.8f)
            curveToRelative(-82.7f, 0.0f, -150.8f, 17.9f, -203.3f, 53.6f)
            curveToRelative(-59.2f, 38.0f, -88.3f, 92.7f, -88.3f, 162.0f)
            curveToRelative(0.0f, 67.1f, 30.2f, 118.4f, 91.6f, 154.2f)
            curveToRelative(19.9f, 10.4f, 61.4f, 26.1f, 123.6f, 46.2f)
            horizontalLineTo(0.0f)
            verticalLineToRelative(93.1f)
            horizontalLineToRelative(681.6f)
            curveToRelative(35.6f, 26.2f, 54.8f, 59.6f, 54.8f, 100.9f)
            curveToRelative(0.0f, 42.4f, -20.1f, 75.9f, -60.3f, 100.6f)
            curveToRelative(-40.2f, 24.6f, -93.8f, 36.9f, -158.7f, 36.9f)
            curveToRelative(-71.5f, 0.0f, -125.1f, -15.6f, -162.0f, -44.7f)
            curveToRelative(-40.2f, -32.4f, -64.8f, -83.8f, -72.6f, -153.1f)
            horizontalLineToRelative(-90.5f)
            curveToRelative(6.7f, 98.3f, 41.3f, 170.9f, 103.9f, 219.0f)
            curveToRelative(53.6f, 40.2f, 127.3f, 60.3f, 221.2f, 60.3f)
            curveToRelative(95.0f, 0.0f, 169.8f, -20.1f, 225.7f, -59.2f)
            curveToRelative(55.9f, -40.2f, 83.8f, -96.1f, 83.8f, -165.3f)
            curveToRelative(0.0f, -35.8f, -8.2f, -67.5f, -24.4f, -95.3f)
            horizontalLineTo(1024.0f)
            verticalLineToRelative(-93.1f)
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
        Image(imageVector = SvgIcons.winEditS(), contentDescription = "")
    }
}
