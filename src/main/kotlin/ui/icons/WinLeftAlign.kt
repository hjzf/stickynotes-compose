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

fun SvgIcons.winLeftAlign(color: Color = Color(0xFF000000)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinLeftAlign", defaultWidth = 1024.0.dp, defaultHeight =
            1024.0.dp, viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(832.0f, 704.0f)
            verticalLineToRelative(64.0f)
            horizontalLineTo(192.0f)
            verticalLineToRelative(-64.0f)
            horizontalLineToRelative(640.0f)
            close()
            moveTo(576.0f, 480.0f)
            verticalLineToRelative(64.0f)
            horizontalLineTo(192.0f)
            verticalLineToRelative(-64.0f)
            horizontalLineToRelative(384.0f)
            close()
            moveTo(832.0f, 256.0f)
            verticalLineToRelative(64.0f)
            horizontalLineTo(192.0f)
            verticalLineTo(256.0f)
            horizontalLineToRelative(640.0f)
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
        Image(imageVector = SvgIcons.winLeftAlign(), contentDescription = "")
    }
}
