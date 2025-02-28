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

fun SvgIcons.winSearch(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinSearch", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(672.0f, 64.0f)
            curveTo(513.3f, 64.0f, 384.0f, 193.3f, 384.0f, 352.0f)
            arcToRelative(
                284.8f, 284.8f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 64.0f,
                dy1 = 178.8f
            )
            lineTo(129.5f, 849.3f)
            lineToRelative(45.1f, 45.4f)
            lineTo(493.2f, 576.0f)
            curveToRelative(49.3f, 39.5f, 111.0f, 64.0f, 178.8f, 64.0f)
            curveToRelative(158.7f, 0.0f, 288.0f, -129.3f, 288.0f, -288.0f)
            reflectiveCurveTo(830.7f, 64.0f, 672.0f, 64.0f)
            close()
            moveTo(672.0f, 128.0f)
            curveTo(796.0f, 128.0f, 896.0f, 228.0f, 896.0f, 352.0f)
            curveTo(896.0f, 476.0f, 796.0f, 576.0f, 672.0f, 576.0f)
            arcTo(223.6f, 223.6f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 448.0f, y1 = 352.0f)
            curveTo(448.0f, 228.0f, 548.0f, 128.0f, 672.0f, 128.0f)
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
        Image(imageVector = SvgIcons.winSearch(), contentDescription = "")
    }
}
