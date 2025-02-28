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

val SvgIcons.CopyImage: ImageVector
    get() {
        if (cache != null) {
            return cache!!
        }
        cache = Builder(name = "CopyImage", defaultWidth = 1024.0.dp, defaultHeight =
                1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f).apply {
            path(fill = SolidColor(Color(0xFF90A4AE)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(213.3f, 298.7f)
                horizontalLineToRelative(725.3f)
                verticalLineToRelative(554.7f)
                horizontalLineTo(213.3f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(256.0f, 341.3f)
                horizontalLineToRelative(640.0f)
                verticalLineToRelative(469.3f)
                horizontalLineTo(256.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF4FC3F7)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(298.7f, 384.0f)
                horizontalLineToRelative(554.7f)
                verticalLineToRelative(256.0f)
                horizontalLineTo(298.7f)
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(298.7f, 628.3f)
                horizontalLineToRelative(554.7f)
                verticalLineToRelative(-87.3f)
                lineToRelative(-554.7f, -17.3f)
                verticalLineToRelative(104.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1976D2)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(298.7f, 682.7f)
                horizontalLineToRelative(554.7f)
                verticalLineToRelative(85.3f)
                horizontalLineTo(298.7f)
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(723.8f, 541.0f)
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 110.9f,
                    dy1 = 0.0f
                )
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -110.9f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(650.0f, 523.7f)
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 110.9f,
                    dy1 = 0.0f
                )
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -110.9f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(539.1f, 523.7f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(594.6f, 523.7f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(298.7f, 523.7f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(335.8f, 506.2f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(465.1f, 506.2f)
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 110.9f,
                    dy1 = 0.0f
                )
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -110.9f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(391.0f, 523.7f)
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 110.9f,
                    dy1 = 0.0f
                )
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -110.9f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(779.5f, 541.0f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFF0D47A1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(298.7f, 682.7f)
                horizontalLineToRelative(554.7f)
                verticalLineToRelative(-52.3f)
                horizontalLineToRelative(-55.5f)
                lineToRelative(-92.4f, -35.0f)
                lineToRelative(-92.6f, 35.0f)
                lineToRelative(-147.8f, -104.7f)
                lineToRelative(-166.4f, 104.7f)
                verticalLineTo(682.7f)
                close()
            }
            path(fill = SolidColor(Color(0xFF90A4AE)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(85.3f, 170.7f)
                horizontalLineToRelative(725.3f)
                verticalLineToRelative(554.7f)
                horizontalLineTo(85.3f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(128.0f, 213.3f)
                horizontalLineToRelative(640.0f)
                verticalLineToRelative(469.3f)
                horizontalLineTo(128.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF4FC3F7)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(170.7f, 256.0f)
                horizontalLineToRelative(554.7f)
                verticalLineToRelative(256.0f)
                horizontalLineTo(170.7f)
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(170.7f, 500.3f)
                horizontalLineToRelative(554.7f)
                verticalLineToRelative(-87.3f)
                lineToRelative(-554.7f, -17.3f)
                verticalLineToRelative(104.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1976D2)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(170.7f, 554.7f)
                horizontalLineToRelative(554.7f)
                verticalLineToRelative(85.3f)
                horizontalLineTo(170.7f)
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(595.8f, 413.0f)
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 110.9f,
                    dy1 = 0.0f
                )
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -110.9f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(522.0f, 395.7f)
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 110.9f,
                    dy1 = 0.0f
                )
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -110.9f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(411.1f, 395.7f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(466.6f, 395.7f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(170.7f, 395.7f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(207.8f, 378.2f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(337.1f, 378.2f)
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 110.9f,
                    dy1 = 0.0f
                )
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -110.9f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(263.0f, 395.7f)
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 110.9f,
                    dy1 = 0.0f
                )
                arcToRelative(55.5f, 52.3f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -110.9f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(651.5f, 413.0f)
                arcToRelative(36.9f, 35.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 73.8f, dy1 = 0.0f)
                arcToRelative(36.9f, 35.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -73.8f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFF0D47A1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(170.7f, 554.7f)
                horizontalLineToRelative(554.7f)
                verticalLineToRelative(-52.3f)
                horizontalLineToRelative(-55.5f)
                lineToRelative(-92.4f, -35.0f)
                lineToRelative(-92.6f, 35.0f)
                lineToRelative(-147.8f, -104.7f)
                lineTo(170.7f, 502.4f)
                verticalLineTo(554.7f)
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
        Image(imageVector = SvgIcons.CopyImage, contentDescription = "")
    }
}
