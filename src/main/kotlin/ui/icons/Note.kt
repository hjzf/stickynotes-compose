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

val SvgIcons.Note: ImageVector
    get() {
        if (cache != null) {
            return cache!!
        }
        cache = Builder(name = "Note", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
                viewportWidth = 1024.0f, viewportHeight = 1024.0f).apply {
            path(fill = SolidColor(Color(0xFF00b180)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(512.0f, 512.0f)
                moveToRelative(-512.0f, 0.0f)
                arcToRelative(512.0f, 512.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 1024.0f,
                    dy1 = 0.0f
                )
                arcToRelative(512.0f, 512.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -1024.0f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(640.0f, 250.9f)
                lineTo(304.6f, 250.9f)
                arcToRelative(51.2f, 51.2f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = -48.6f,
                    dy1 = 48.6f
                )
                verticalLineToRelative(447.7f)
                arcToRelative(51.2f, 51.2f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = 57.1f,
                    dy1 = 51.2f
                )
                horizontalLineToRelative(392.4f)
                arcToRelative(52.5f, 52.5f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = 49.2f,
                    dy1 = -49.4f
                )
                lineTo(754.7f, 358.4f)
                lineToRelative(-51.2f, 67.1f)
                verticalLineToRelative(323.6f)
                lineTo(307.2f, 748.0f)
                lineTo(307.2f, 300.5f)
                horizontalLineToRelative(297.5f)
                lineTo(640.0f, 250.9f)
                close()
                moveTo(698.9f, 252.9f)
                lineToRelative(52.7f, 41.2f)
                lineToRelative(11.0f, -14.6f)
                arcToRelative(22.0f, 22.0f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = 0.0f,
                    dy1 = -25.6f
                )
                arcToRelative(63.7f, 63.7f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = -20.2f,
                    dy1 = -19.2f
                )
                arcToRelative(25.6f, 25.6f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = -29.4f,
                    dy1 = 2.0f
                )
                lineToRelative(-13.8f, 16.1f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(736.3f, 315.6f)
                lineToRelative(-120.3f, 161.3f)
                arcToRelative(56.3f, 56.3f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    dx1 = -22.0f,
                    dy1 = 21.5f
                )
                lineToRelative(-39.7f, 18.9f)
                reflectiveCurveToRelative(-10.8f, 5.4f, -13.1f, -9.5f)
                lineToRelative(6.9f, -43.8f)
                arcToRelative(37.1f, 37.1f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    dx1 = 10.0f,
                    dy1 = -22.0f
                )
                lineToRelative(125.7f, -167.9f)
                lineToRelative(52.5f, 41.5f)
                close()
                moveTo(386.8f, 561.4f)
                horizontalLineToRelative(235.8f)
                reflectiveCurveToRelative(7.7f, -2.0f, 7.4f, 11.0f)
                verticalLineToRelative(22.0f)
                reflectiveCurveToRelative(-1.8f, 6.1f, -10.8f, 4.6f)
                horizontalLineToRelative(-230.4f)
                arcToRelative(7.9f, 7.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -8.7f, dy1 = -7.2f)
                verticalLineToRelative(-25.6f)
                arcToRelative(6.9f, 6.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 6.4f, dy1 = -5.4f)
                close()
                moveTo(386.8f, 415.2f)
                horizontalLineToRelative(111.1f)
                reflectiveCurveToRelative(7.7f, -2.0f, 7.4f, 11.0f)
                lineTo(505.3f, 486.4f)
                reflectiveCurveToRelative(-1.8f, 6.1f, -10.8f, 4.6f)
                lineTo(389.1f, 491.0f)
                arcToRelative(7.9f, 7.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -8.7f, dy1 = -7.2f)
                verticalLineToRelative(-63.0f)
                arcToRelative(6.9f, 6.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 6.4f, dy1 = -5.4f)
                close()
                moveTo(386.8f, 649.0f)
                horizontalLineToRelative(235.8f)
                reflectiveCurveToRelative(7.7f, -2.0f, 7.4f, 11.0f)
                verticalLineToRelative(22.0f)
                reflectiveCurveToRelative(-1.8f, 6.1f, -10.8f, 4.6f)
                horizontalLineToRelative(-230.4f)
                arcToRelative(7.9f, 7.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -8.7f, dy1 = -7.2f)
                verticalLineToRelative(-25.6f)
                arcToRelative(6.9f, 6.9f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 6.4f, dy1 = -5.4f)
                close()
            }
        }
        .build()
        return cache!!
    }

private var cache: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SvgIcons.Note, contentDescription = "")
    }
}
