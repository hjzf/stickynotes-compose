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

fun SvgIcons.winInfo(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinInfo", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(536.0f, 560.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = 16.0f)
            horizontalLineToRelative(-16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = -16.0f)
            lineTo(488.0f, 368.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = -16.0f)
            horizontalLineToRelative(16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = 16.0f)
            verticalLineToRelative(192.0f)
            close()
            moveTo(504.0f, 688.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = -16.0f)
            verticalLineToRelative(-32.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = -16.0f)
            horizontalLineToRelative(16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = 16.0f)
            verticalLineToRelative(32.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = 16.0f)
            horizontalLineToRelative(-16.0f)
            close()
            moveTo(512.0f, 800.0f)
            curveToRelative(159.1f, 0.0f, 288.0f, -128.9f, 288.0f, -288.0f)
            reflectiveCurveToRelative(-128.9f, -288.0f, -288.0f, -288.0f)
            reflectiveCurveToRelative(-288.0f, 128.9f, -288.0f, 288.0f)
            reflectiveCurveToRelative(128.9f, 288.0f, 288.0f, 288.0f)
            close()
            moveTo(512.0f, 848.0f)
            curveToRelative(-185.6f, 0.0f, -336.0f, -150.4f, -336.0f, -336.0f)
            reflectiveCurveToRelative(150.4f, -336.0f, 336.0f, -336.0f)
            reflectiveCurveToRelative(336.0f, 150.4f, 336.0f, 336.0f)
            reflectiveCurveToRelative(-150.4f, 336.0f, -336.0f, 336.0f)
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
        Image(imageVector = SvgIcons.winInfo(), contentDescription = "")
    }
}
