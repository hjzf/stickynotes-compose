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

fun SvgIcons.winDelete(color: Color = Color(0xFFff0000)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinDelete", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(656.0f, 288.0f)
            horizontalLineToRelative(144.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = 16.0f)
            verticalLineToRelative(16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = 16.0f)
            horizontalLineToRelative(-48.0f)
            verticalLineToRelative(496.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = 16.0f)
            lineTo(288.0f, 848.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = -16.0f)
            lineTo(272.0f, 336.0f)
            horizontalLineToRelative(-48.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = -16.0f)
            verticalLineToRelative(-16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = -16.0f)
            horizontalLineToRelative(144.0f)
            verticalLineToRelative(-80.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = -16.0f)
            horizontalLineToRelative(256.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = 16.0f)
            verticalLineToRelative(80.0f)
            close()
            moveTo(608.0f, 288.0f)
            verticalLineToRelative(-48.0f)
            lineTo(416.0f, 240.0f)
            verticalLineToRelative(48.0f)
            horizontalLineToRelative(192.0f)
            close()
            moveTo(640.0f, 336.0f)
            lineTo(320.0f, 336.0f)
            verticalLineToRelative(464.0f)
            horizontalLineToRelative(384.0f)
            lineTo(704.0f, 336.0f)
            horizontalLineToRelative(-64.0f)
            close()
            moveTo(432.0f, 448.0f)
            horizontalLineToRelative(16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = 16.0f)
            verticalLineToRelative(192.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = 16.0f)
            horizontalLineToRelative(-16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = -16.0f)
            lineTo(416.0f, 464.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = -16.0f)
            close()
            moveTo(576.0f, 448.0f)
            horizontalLineToRelative(16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = 16.0f)
            verticalLineToRelative(192.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = 16.0f)
            horizontalLineToRelative(-16.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = -16.0f, dy1 = -16.0f)
            lineTo(560.0f, 464.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 16.0f, dy1 = -16.0f)
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
        Image(imageVector = SvgIcons.winDelete(), contentDescription = "")
    }
}
