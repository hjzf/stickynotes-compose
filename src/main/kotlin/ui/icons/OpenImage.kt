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

val SvgIcons.OpenImage: ImageVector
    get() {
        if (cache != null) {
            return cache!!
        }
        cache = Builder(name = "OpenImage", defaultWidth = 1024.0.dp, defaultHeight =
                1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f).apply {
            path(fill = SolidColor(Color(0xFF90A4AE)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(106.7f, 192.0f)
                horizontalLineToRelative(810.7f)
                verticalLineToRelative(640.0f)
                horizontalLineTo(106.7f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(149.3f, 234.7f)
                horizontalLineToRelative(725.3f)
                verticalLineToRelative(554.7f)
                horizontalLineTo(149.3f)
                close()
            }
            path(fill = SolidColor(Color(0xFF4FC3F7)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(192.0f, 277.3f)
                horizontalLineToRelative(640.0f)
                verticalLineToRelative(298.7f)
                horizontalLineTo(192.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(192.0f, 576.0f)
                horizontalLineToRelative(640.0f)
                verticalLineToRelative(-106.7f)
                lineTo(192.0f, 448.0f)
                verticalLineToRelative(128.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1976D2)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(192.0f, 640.0f)
                horizontalLineToRelative(640.0f)
                verticalLineToRelative(106.7f)
                horizontalLineTo(192.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(746.7f, 469.3f)
                moveToRelative(-64.0f, 0.0f)
                arcToRelative(64.0f, 64.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 128.0f,
                    dy1 = 0.0f
                )
                arcToRelative(64.0f, 64.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -128.0f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(661.3f, 448.0f)
                moveToRelative(-64.0f, 0.0f)
                arcToRelative(64.0f, 64.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 128.0f,
                    dy1 = 0.0f
                )
                arcToRelative(64.0f, 64.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -128.0f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(512.0f, 448.0f)
                moveToRelative(-42.7f, 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 85.3f, dy1 = 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -85.3f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(576.0f, 448.0f)
                moveToRelative(-42.7f, 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 85.3f, dy1 = 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -85.3f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(234.7f, 448.0f)
                moveToRelative(-42.7f, 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 85.3f, dy1 = 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -85.3f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(277.3f, 426.7f)
                moveToRelative(-42.7f, 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 85.3f, dy1 = 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -85.3f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(448.0f, 426.7f)
                moveToRelative(-64.0f, 0.0f)
                arcToRelative(64.0f, 64.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 128.0f,
                    dy1 = 0.0f
                )
                arcToRelative(64.0f, 64.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -128.0f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(362.7f, 448.0f)
                moveToRelative(-64.0f, 0.0f)
                arcToRelative(64.0f, 64.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = 128.0f,
                    dy1 = 0.0f
                )
                arcToRelative(64.0f, 64.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -128.0f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFFB3E5FC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(789.3f, 469.3f)
                moveToRelative(-42.7f, 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, dx1 = 85.3f, dy1 = 0.0f)
                arcToRelative(42.7f, 42.7f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = false,
                    dx1 = -85.3f,
                    dy1 = 0.0f
                )
                close()
            }
            path(fill = SolidColor(Color(0xFF0D47A1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(192.0f, 640.0f)
                horizontalLineToRelative(640.0f)
                verticalLineToRelative(-64.0f)
                horizontalLineToRelative(-64.0f)
                lineToRelative(-106.7f, -42.7f)
                lineToRelative(-106.7f, 42.7f)
                lineToRelative(-170.7f, -128.0f)
                lineToRelative(-192.0f, 128.0f)
                verticalLineToRelative(64.0f)
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
        Image(imageVector = SvgIcons.OpenImage, contentDescription = "")
    }
}
