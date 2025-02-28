package ui.icons

import androidx.compose.desktop.ui.tooling.preview.Preview
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
import androidx.compose.ui.unit.dp
import ui.SvgIcons

fun SvgIcons.noImage(color: Color = Color(0xFF2c2c2c)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "NoImage", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(692.0f, 339.5f)
            arcToRelative(
                67.5f, 67.5f, 0.0f,
                isMoreThanHalf = true,
                isPositiveArc = false,
                dx1 = 135.0f,
                dy1 = 0.0f
            )
            arcToRelative(
                67.5f, 67.5f, 0.0f,
                isMoreThanHalf = true,
                isPositiveArc = false,
                dx1 = -135.0f,
                dy1 = 0.0f
            )
            close()
            moveTo(969.4f, 141.3f)
            arcToRelative(
                30.0f, 30.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -20.6f,
                dy1 = -10.2f
            )
            lineToRelative(-427.7f, -29.9f)
            arcToRelative(
                29.6f, 29.6f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -25.5f,
                dy1 = 11.2f
            )
            lineToRelative(-86.4f, 107.7f)
            arcToRelative(
                30.0f, 30.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 0.6f,
                dy1 = 38.3f
            )
            lineToRelative(137.0f, 159.3f)
            lineToRelative(-138.2f, 182.5f)
            lineToRelative(34.2f, -162.5f)
            arcToRelative(
                30.0f, 30.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -10.4f,
                dy1 = -29.5f
            )
            lineTo(252.9f, 262.2f)
            lineToRelative(74.6f, -117.8f)
            arcToRelative(
                30.0f, 30.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -27.5f,
                dy1 = -46.0f
            )
            lineTo(74.9f, 114.1f)
            arcToRelative(
                30.0f, 30.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -27.8f,
                dy1 = 32.0f
            )
            lineToRelative(52.3f, 748.1f)
            arcToRelative(
                30.1f, 30.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 32.2f,
                dy1 = 27.8f
            )
            lineToRelative(165.5f, -12.4f)
            curveToRelative(16.5f, -1.2f, 28.9f, -15.6f, 27.7f, -32.1f)
            reflectiveCurveToRelative(-15.5f, -29.1f, -32.2f, -27.7f)
            lineTo(157.1f, 860.0f)
            lineToRelative(-6.0f, -86.7f)
            lineToRelative(117.5f, -11.8f)
            arcToRelative(
                30.0f, 30.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 26.9f,
                dy1 = -32.8f
            )
            arcToRelative(
                30.1f, 30.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -32.8f,
                dy1 = -26.9f
            )
            lineTo(146.9f, 713.4f)
            lineToRelative(-37.9f, -541.6f)
            lineToRelative(136.1f, -9.5f)
            lineToRelative(-57.2f, 90.2f)
            arcToRelative(
                30.0f, 30.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 6.4f,
                dy1 = 39.3f
            )
            lineToRelative(186.0f, 151.3f)
            lineToRelative(-55.1f, 261.8f)
            arcToRelative(
                29.0f, 29.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 0.7f,
                dy1 = 13.8f
            )
            arcToRelative(
                29.7f, 29.7f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 1.3f,
                dy1 = 10.4f
            )
            lineToRelative(48.3f, 144.9f)
            arcToRelative(
                30.0f, 30.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 25.7f,
                dy1 = 20.4f
            )
            lineToRelative(490.4f, 44.6f)
            arcToRelative(
                30.1f, 30.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 32.7f,
                dy1 = -27.8f
            )
            lineTo(976.7f, 163.1f)
            arcToRelative(
                30.1f, 30.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -7.3f,
                dy1 = -21.8f
            )
            close()
            moveTo(876.7f, 732.7f)
            lineToRelative(-386.8f, -37.2f)
            arcToRelative(
                29.5f, 29.5f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -32.7f,
                dy1 = 27.0f
            )
            arcToRelative(
                30.0f, 30.0f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 27.0f,
                dy1 = 32.7f
            )
            lineToRelative(388.4f, 37.3f)
            lineToRelative(-5.9f, 83.9f)
            lineToRelative(-440.4f, -40.0f)
            lineToRelative(-37.1f, -111.3f)
            lineToRelative(220.1f, -290.6f)
            arcToRelative(
                29.9f, 29.9f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -1.2f,
                dy1 = -37.7f
            )
            lineTo(471.6f, 238.1f)
            lineToRelative(61.0f, -76.0f)
            lineToRelative(382.1f, 26.7f)
            lineToRelative(-38.0f, 543.9f)
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
        Image(imageVector = SvgIcons.noImage(), contentDescription = "")
    }
}