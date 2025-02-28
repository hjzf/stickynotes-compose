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

fun SvgIcons.winSetting(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinSetting", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(512.0f, 315.9f)
            curveToRelative(-108.3f, 0.0f, -196.1f, 87.8f, -196.1f, 196.1f)
            reflectiveCurveTo(403.7f, 708.1f, 512.0f, 708.1f)
            reflectiveCurveTo(708.1f, 620.3f, 708.1f, 512.0f)
            reflectiveCurveTo(620.3f, 315.9f, 512.0f, 315.9f)
            close()
            moveTo(605.4f, 605.4f)
            arcTo(132.1f, 132.1f, 0.0f, isMoreThanHalf = true, isPositiveArc = true, x1 = 644.1f, y1 = 512.0f)
            arcToRelative(131.3f, 131.3f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -38.7f, dy1 = 93.4f)
            close()
        }
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(851.4f, 512.0f)
            curveToRelative(0.0f, -7.1f, -0.2f, -14.3f, -0.7f, -21.5f)
            lineToRelative(68.8f, -62.2f)
            lineToRelative(-4.7f, -18.7f)
            arcToRelative(
                413.1f, 413.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -45.6f,
                dy1 = -110.0f
            )
            lineTo(859.3f, 283.0f)
            lineToRelative(-92.6f, 4.7f)
            arcToRelative(
                340.5f, 340.5f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -30.5f,
                dy1 = -30.5f
            )
            lineToRelative(4.8f, -92.6f)
            lineToRelative(-16.6f, -9.9f)
            arcToRelative(
                413.1f, 413.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -110.0f,
                dy1 = -45.6f
            )
            lineToRelative(-18.7f, -4.7f)
            lineToRelative(-62.2f, 68.8f)
            arcToRelative(336.6f, 336.6f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -43.1f, dy1 = 0.0f)
            lineToRelative(-62.2f, -68.8f)
            lineToRelative(-18.7f, 4.7f)
            arcToRelative(
                413.1f, 413.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -110.0f,
                dy1 = 45.6f
            )
            lineTo(283.0f, 164.7f)
            lineToRelative(4.7f, 92.6f)
            arcToRelative(
                341.8f, 341.8f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -30.5f,
                dy1 = 30.5f
            )
            lineTo(164.7f, 283.0f)
            lineToRelative(-9.9f, 16.6f)
            arcToRelative(
                413.1f, 413.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = -45.6f,
                dy1 = 110.0f
            )
            lineToRelative(-4.7f, 18.7f)
            lineToRelative(68.8f, 62.2f)
            curveToRelative(-0.5f, 7.2f, -0.7f, 14.4f, -0.7f, 21.5f)
            reflectiveCurveToRelative(0.2f, 14.3f, 0.7f, 21.5f)
            lineToRelative(-68.8f, 62.2f)
            lineToRelative(4.7f, 18.7f)
            arcToRelative(
                413.1f, 413.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 45.6f,
                dy1 = 110.0f
            )
            lineToRelative(9.9f, 16.7f)
            lineToRelative(92.6f, -4.7f)
            arcToRelative(340.5f, 340.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 30.5f, dy1 = 30.5f)
            lineTo(283.0f, 859.3f)
            lineToRelative(16.6f, 9.9f)
            arcToRelative(
                413.1f, 413.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 110.0f,
                dy1 = 45.6f
            )
            lineToRelative(18.7f, 4.7f)
            lineToRelative(62.2f, -68.8f)
            arcToRelative(336.6f, 336.6f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = 43.1f, dy1 = 0.0f)
            lineToRelative(62.2f, 68.8f)
            lineToRelative(18.7f, -4.7f)
            arcToRelative(
                413.1f, 413.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 110.0f,
                dy1 = -45.6f
            )
            lineToRelative(16.7f, -9.9f)
            lineToRelative(-4.7f, -92.6f)
            arcToRelative(
                341.8f, 341.8f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 30.5f,
                dy1 = -30.5f
            )
            lineToRelative(92.6f, 4.8f)
            lineToRelative(9.9f, -16.6f)
            arcToRelative(
                413.1f, 413.1f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = false,
                dx1 = 45.6f,
                dy1 = -110.0f
            )
            lineToRelative(4.7f, -18.7f)
            lineToRelative(-68.8f, -62.2f)
            curveToRelative(0.5f, -7.3f, 0.7f, -14.5f, 0.7f, -21.6f)
            close()
            moveTo(785.6f, 480.9f)
            arcToRelative(276.5f, 276.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 0.0f, dy1 = 62.1f)
            lineToRelative(-1.8f, 16.3f)
            lineTo(847.6f, 617.0f)
            arcToRelative(349.9f, 349.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -24.1f, dy1 = 58.1f)
            lineToRelative(-85.8f, -4.4f)
            lineToRelative(-10.3f, 12.9f)
            arcToRelative(277.6f, 277.6f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -43.9f, dy1 = 43.9f)
            lineToRelative(-12.9f, 10.3f)
            lineToRelative(4.4f, 85.8f)
            arcToRelative(349.9f, 349.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -58.1f, dy1 = 24.1f)
            lineToRelative(-57.6f, -63.8f)
            lineToRelative(-16.3f, 1.8f)
            arcToRelative(276.5f, 276.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -62.1f, dy1 = 0.0f)
            lineToRelative(-16.3f, -1.8f)
            lineTo(407.0f, 847.6f)
            arcToRelative(
                349.9f, 349.9f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = true,
                dx1 = -58.1f,
                dy1 = -24.1f
            )
            lineToRelative(4.4f, -85.8f)
            lineToRelative(-12.9f, -10.3f)
            arcToRelative(
                277.7f, 277.7f, 0.0f,
                isMoreThanHalf = false,
                isPositiveArc = true,
                dx1 = -43.9f,
                dy1 = -43.9f
            )
            lineToRelative(-10.2f, -12.9f)
            lineToRelative(-85.8f, 4.4f)
            arcTo(349.0f, 349.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 176.4f, y1 = 617.0f)
            lineToRelative(63.8f, -57.6f)
            lineToRelative(-1.8f, -16.3f)
            arcToRelative(276.5f, 276.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 0.0f, dy1 = -62.1f)
            lineToRelative(1.8f, -16.3f)
            lineTo(176.4f, 407.0f)
            arcToRelative(349.9f, 349.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 24.1f, dy1 = -58.1f)
            lineToRelative(85.8f, 4.4f)
            lineToRelative(10.2f, -12.9f)
            arcToRelative(278.6f, 278.6f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 43.9f, dy1 = -43.9f)
            lineToRelative(12.9f, -10.2f)
            lineToRelative(-4.4f, -85.8f)
            arcToRelative(349.9f, 349.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 58.1f, dy1 = -24.1f)
            lineToRelative(57.6f, 63.8f)
            lineToRelative(16.3f, -1.8f)
            arcToRelative(276.5f, 276.5f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 62.1f, dy1 = 0.0f)
            lineToRelative(16.3f, 1.8f)
            lineTo(617.0f, 176.4f)
            arcToRelative(349.0f, 349.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 58.1f, dy1 = 24.1f)
            lineToRelative(-4.4f, 85.8f)
            lineToRelative(12.9f, 10.2f)
            arcToRelative(277.7f, 277.7f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 43.9f, dy1 = 43.9f)
            lineToRelative(10.3f, 12.9f)
            lineToRelative(85.8f, -4.4f)
            arcToRelative(349.9f, 349.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 24.1f, dy1 = 58.1f)
            lineToRelative(-63.8f, 57.6f)
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
        Image(imageVector = SvgIcons.winSetting(), contentDescription = "")
    }
}
