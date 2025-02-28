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

fun SvgIcons.winCheck(color: Color = Color(0xFF000000)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinCheck", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(373.8f, 758.9f)
            lineTo(167.9f, 540.2f)
            curveToRelative(-12.1f, -12.9f, -11.5f, -33.1f, 1.4f, -45.2f)
            curveToRelative(12.9f, -12.1f, 33.1f, -11.5f, 45.2f, 1.4f)
            lineToRelative(167.1f, 177.5f)
            curveToRelative(5.5f, 5.8f, 14.7f, 5.9f, 20.2f, 0.2f)
            lineToRelative(383.1f, -390.7f)
            curveToRelative(12.4f, -12.6f, 32.6f, -12.8f, 45.3f, -0.4f)
            curveToRelative(12.6f, 12.4f, 12.8f, 32.6f, 0.4f, 45.3f)
            lineTo(407.8f, 759.3f)
            curveToRelative(-9.4f, 9.6f, -24.8f, 9.4f, -34.0f, -0.3f)
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
        Image(imageVector = SvgIcons.winCheck(), contentDescription = "")
    }
}
