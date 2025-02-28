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

fun SvgIcons.winIncrease(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinIncrease", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(488.0f, 488.0f)
            verticalLineTo(192.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = -16.0f)
            horizontalLineToRelative(16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = 16.0f)
            verticalLineToRelative(296.0f)
            horizontalLineTo(832.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = 16.0f)
            verticalLineToRelative(16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = 16.0f)
            horizontalLineTo(536.0f)
            verticalLineTo(832.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = 16.0f)
            horizontalLineToRelative(-16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = -16.0f)
            verticalLineTo(536.0f)
            horizontalLineTo(192.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = -16.0f)
            verticalLineToRelative(-16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = -16.0f)
            horizontalLineToRelative(296.0f)
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
        Image(imageVector = SvgIcons.winIncrease(), contentDescription = "")
    }
}
