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

fun SvgIcons.winTag(color: Color = Color(0xFF000000)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinTag", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(994.0f, 121.1f)
            arcTo(102.4f, 102.4f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, x1 = 902.9f, y1 = 30.0f)
            lineTo(582.5f, 0.3f)
            arcToRelative(
                102.4f, 102.4f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -79.9f,
                dy1 = 29.7f
            )
            lineTo(29.2f, 502.9f)
            arcToRelative(102.4f, 102.4f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 0.0f, dy1 = 142.3f)
            lineToRelative(349.6f, 349.1f)
            arcToRelative(102.4f, 102.4f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 142.3f, dy1 = 0.0f)
            lineToRelative(473.0f, -473.0f)
            arcTo(102.4f, 102.4f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, x1 = 1023.7f, y1 = 441.5f)
            close()
            moveTo(450.4f, 921.6f)
            lineTo(102.4f, 575.1f)
            lineTo(573.3f, 102.6f)
            lineToRelative(318.9f, 27.6f)
            lineToRelative(29.2f, 318.9f)
            close()
        }
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(729.5f, 293.8f)
            moveToRelative(-72.4f, 72.4f)
            arcToRelative(
                102.4f, 102.4f, 0.0f,
                isMoreThanHalf = true,
                isPositiveArc = false,
                dx1 = 144.8f,
                dy1 = -144.8f
            )
            arcToRelative(
                102.4f, 102.4f, 0.0f,
                isMoreThanHalf = true,
                isPositiveArc = false,
                dx1 = -144.8f,
                dy1 = 144.8f
            )
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
        Image(imageVector = SvgIcons.winTag(), contentDescription = "")
    }
}
