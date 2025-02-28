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

fun SvgIcons.winText(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinText", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(832.0f, 64.0f)
            curveToRelative(35.3f, 0.0f, 64.0f, 28.7f, 64.0f, 64.0f)
            verticalLineToRelative(768.0f)
            curveToRelative(0.0f, 35.3f, -28.7f, 64.0f, -64.0f, 64.0f)
            lineTo(192.0f, 960.0f)
            curveToRelative(-35.3f, 0.0f, -64.0f, -28.7f, -64.0f, -64.0f)
            lineTo(128.0f, 128.0f)
            curveToRelative(0.0f, -35.3f, 28.7f, -64.0f, 64.0f, -64.0f)
            horizontalLineToRelative(640.0f)
            close()
            moveTo(832.0f, 112.0f)
            lineTo(192.0f, 112.0f)
            curveToRelative(-8.8f, 0.0f, -16.0f, 7.2f, -16.0f, 16.0f)
            verticalLineToRelative(768.0f)
            curveToRelative(0.0f, 8.8f, 7.2f, 16.0f, 16.0f, 16.0f)
            horizontalLineToRelative(640.0f)
            curveToRelative(8.8f, 0.0f, 16.0f, -7.2f, 16.0f, -16.0f)
            lineTo(848.0f, 128.0f)
            curveToRelative(0.0f, -8.8f, -7.2f, -16.0f, -16.0f, -16.0f)
            close()
            moveTo(680.0f, 704.0f)
            curveToRelative(13.3f, 0.0f, 24.0f, 10.7f, 24.0f, 24.0f)
            curveToRelative(0.0f, 13.3f, -10.7f, 24.0f, -24.0f, 24.0f)
            lineTo(344.0f, 752.0f)
            curveToRelative(-13.3f, 0.0f, -24.0f, -10.7f, -24.0f, -24.0f)
            curveToRelative(0.0f, -13.3f, 10.7f, -24.0f, 24.0f, -24.0f)
            horizontalLineToRelative(336.0f)
            close()
            moveTo(680.0f, 544.0f)
            curveToRelative(13.3f, 0.0f, 24.0f, 10.7f, 24.0f, 24.0f)
            curveToRelative(0.0f, 13.3f, -10.7f, 24.0f, -24.0f, 24.0f)
            lineTo(344.0f, 592.0f)
            curveToRelative(-13.3f, 0.0f, -24.0f, -10.7f, -24.0f, -24.0f)
            curveToRelative(0.0f, -13.3f, 10.7f, -24.0f, 24.0f, -24.0f)
            horizontalLineToRelative(336.0f)
            close()
            moveTo(680.0f, 400.0f)
            curveToRelative(13.3f, 0.0f, 24.0f, 10.7f, 24.0f, 24.0f)
            curveToRelative(0.0f, 13.3f, -10.7f, 24.0f, -24.0f, 24.0f)
            lineTo(344.0f, 448.0f)
            curveToRelative(-13.3f, 0.0f, -24.0f, -10.7f, -24.0f, -24.0f)
            curveToRelative(0.0f, -13.3f, 10.7f, -24.0f, 24.0f, -24.0f)
            horizontalLineToRelative(336.0f)
            close()
            moveTo(488.0f, 256.0f)
            curveToRelative(13.3f, 0.0f, 24.0f, 10.7f, 24.0f, 24.0f)
            curveToRelative(0.0f, 13.3f, -10.7f, 24.0f, -24.0f, 24.0f)
            horizontalLineToRelative(-144.0f)
            curveToRelative(-13.3f, 0.0f, -24.0f, -10.7f, -24.0f, -24.0f)
            curveToRelative(0.0f, -13.3f, 10.7f, -24.0f, 24.0f, -24.0f)
            horizontalLineToRelative(144.0f)
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
        Image(imageVector = SvgIcons.winText(), contentDescription = "")
    }
}
