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

fun SvgIcons.winEditB(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinEditB", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(582.8f, 125.8f)
            curveToRelative(78.2f, 0.0f, 138.5f, 17.9f, 183.2f, 55.9f)
            curveToRelative(42.4f, 35.8f, 63.7f, 83.8f, 63.7f, 145.3f)
            curveToRelative(0.0f, 44.7f, -11.2f, 82.7f, -33.5f, 113.9f)
            curveToRelative(-22.3f, 29.0f, -52.5f, 51.4f, -92.7f, 65.9f)
            curveToRelative(52.5f, 10.1f, 92.7f, 31.3f, 119.5f, 63.7f)
            curveToRelative(26.8f, 32.4f, 40.2f, 75.9f, 40.2f, 129.6f)
            curveToRelative(0.0f, 80.4f, -27.9f, 138.5f, -82.7f, 176.5f)
            curveToRelative(-46.9f, 31.3f, -112.8f, 46.9f, -197.8f, 46.9f)
            horizontalLineToRelative(-364.2f)
            lineTo(218.6f, 125.8f)
            horizontalLineToRelative(364.2f)
            close()
            moveTo(340.4f, 464.3f)
            horizontalLineToRelative(210.0f)
            curveToRelative(55.9f, 0.0f, 96.1f, -10.1f, 120.7f, -29.0f)
            curveToRelative(24.6f, -20.1f, 36.9f, -51.4f, 36.9f, -93.8f)
            curveToRelative(0.0f, -40.2f, -12.3f, -69.3f, -36.9f, -87.2f)
            curveToRelative(-24.6f, -19.0f, -64.8f, -27.9f, -118.4f, -27.9f)
            lineTo(340.4f, 226.3f)
            verticalLineToRelative(238.0f)
            close()
            moveTo(340.4f, 822.9f)
            horizontalLineToRelative(225.7f)
            curveToRelative(51.4f, 0.0f, 91.6f, -8.9f, 120.6f, -24.6f)
            curveToRelative(35.8f, -21.2f, 54.8f, -54.8f, 54.8f, -100.6f)
            curveToRelative(0.0f, -46.9f, -14.5f, -81.6f, -42.5f, -102.8f)
            curveToRelative(-27.9f, -21.2f, -71.5f, -31.3f, -129.6f, -31.3f)
            lineTo(340.4f, 563.7f)
            verticalLineToRelative(259.2f)
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
        Image(imageVector = SvgIcons.winEditB(), contentDescription = "")
    }
}
