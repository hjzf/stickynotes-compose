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

fun SvgIcons.winMessage(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinMessage", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(125.2f, 182.0f)
            verticalLineToRelative(523.4f)
            arcToRelative(68.3f, 68.3f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 68.3f, dy1 = 68.3f)
            horizontalLineToRelative(320.5f)
            lineToRelative(129.3f, 129.3f)
            curveToRelative(5.7f, 5.6f, 16.9f, 11.1f, 24.2f, 9.6f)
            curveToRelative(2.3f, 0.8f, -2.6f, 0.0f, 0.0f, 0.0f)
            curveToRelative(12.6f, 0.0f, 26.7f, -12.5f, 26.7f, -25.1f)
            lineTo(694.0f, 773.7f)
            horizontalLineToRelative(136.5f)
            curveToRelative(37.7f, 0.0f, 68.3f, -30.6f, 68.3f, -68.3f)
            lineTo(898.8f, 182.0f)
            arcToRelative(68.3f, 68.3f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -68.3f, dy1 = -68.3f)
            lineTo(193.4f, 113.8f)
            arcToRelative(68.3f, 68.3f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -68.3f, dy1 = 68.3f)
            close()
            moveTo(170.7f, 204.8f)
            arcToRelative(45.5f, 45.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 45.5f, dy1 = -45.5f)
            horizontalLineToRelative(591.6f)
            arcToRelative(45.5f, 45.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 45.5f, dy1 = 45.5f)
            verticalLineToRelative(477.9f)
            arcToRelative(45.5f, 45.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -45.5f, dy1 = 45.5f)
            lineTo(671.3f, 728.2f)
            arcToRelative(22.8f, 22.8f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -22.8f, dy1 = 22.8f)
            verticalLineToRelative(93.0f)
            lineToRelative(-108.5f, -108.5f)
            curveToRelative(-1.6f, -1.5f, -5.9f, -6.2f, -7.8f, -7.2f)
            lineTo(216.2f, 728.2f)
            arcToRelative(45.5f, 45.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -45.5f, dy1 = -45.5f)
            lineTo(170.7f, 204.8f)
            close()
            moveTo(330.0f, 386.9f)
            arcToRelative(45.5f, 45.5f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 45.5f, dy1 = 45.5f)
            arcToRelative(45.5f, 45.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -45.5f, dy1 = -45.5f)
            close()
            moveTo(512.0f, 386.9f)
            arcToRelative(45.5f, 45.5f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = -0.0f, dy1 = 91.0f)
            arcTo(45.5f, 45.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, x1 = 512.0f, y1 = 386.9f)
            close()
            moveTo(694.0f, 386.9f)
            arcToRelative(45.5f, 45.5f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 0.0f, dy1 = 91.0f)
            arcToRelative(45.5f, 45.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 0.0f, dy1 = -91.0f)
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
        Image(imageVector = SvgIcons.winMessage(), contentDescription = "")
    }
}
