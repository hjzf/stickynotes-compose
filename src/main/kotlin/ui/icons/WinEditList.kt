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

fun SvgIcons.winEditList(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinEditList", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(280.3f, 181.3f)
            horizontalLineToRelative(708.8f)
            verticalLineToRelative(93.1f)
            horizontalLineTo(280.3f)
            close()
            moveTo(173.1f, 297.7f)
            horizontalLineTo(36.4f)
            curveToRelative(-0.8f, 0.0f, -1.5f, -0.7f, -1.5f, -1.5f)
            verticalLineTo(159.5f)
            curveToRelative(0.0f, -0.8f, 0.7f, -1.5f, 1.5f, -1.5f)
            horizontalLineToRelative(136.6f)
            curveToRelative(0.8f, 0.0f, 1.5f, 0.7f, 1.5f, 1.5f)
            verticalLineToRelative(136.6f)
            curveToRelative(0.0f, 0.8f, -0.7f, 1.5f, -1.5f, 1.5f)
            close()
            moveTo(280.3f, 478.8f)
            horizontalLineToRelative(708.8f)
            verticalLineToRelative(93.1f)
            horizontalLineTo(280.3f)
            close()
            moveTo(173.1f, 595.1f)
            horizontalLineTo(36.4f)
            curveToRelative(-0.8f, 0.0f, -1.5f, -0.7f, -1.5f, -1.5f)
            verticalLineTo(457.0f)
            curveToRelative(0.0f, -0.8f, 0.7f, -1.5f, 1.5f, -1.5f)
            horizontalLineToRelative(136.6f)
            curveToRelative(0.8f, 0.0f, 1.5f, 0.7f, 1.5f, 1.5f)
            verticalLineToRelative(136.6f)
            curveToRelative(0.0f, 0.8f, -0.7f, 1.5f, -1.5f, 1.5f)
            close()
        }
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(280.3f, 776.3f)
            horizontalLineToRelative(708.8f)
            verticalLineToRelative(93.1f)
            horizontalLineTo(280.3f)
            close()
            moveTo(173.1f, 892.6f)
            horizontalLineTo(36.4f)
            curveToRelative(-0.8f, 0.0f, -1.5f, -0.7f, -1.5f, -1.5f)
            verticalLineTo(754.5f)
            curveToRelative(0.0f, -0.8f, 0.7f, -1.5f, 1.5f, -1.5f)
            horizontalLineToRelative(136.6f)
            curveToRelative(0.8f, 0.0f, 1.5f, 0.7f, 1.5f, 1.5f)
            verticalLineToRelative(136.6f)
            curveToRelative(0.0f, 0.8f, -0.7f, 1.5f, -1.5f, 1.5f)
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
        Image(imageVector = SvgIcons.winEditList(), contentDescription = "")
    }
}
