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

fun SvgIcons.winEditU(color: Color = Color(0xFF636363)): ImageVector {
    var imageVector = cache[color]
    if (imageVector != null) {
        return imageVector
    }
    imageVector = Builder(
        name = "WinEditU", defaultWidth = 1024.0.dp, defaultHeight = 1024.0.dp,
        viewportWidth = 1024.0f, viewportHeight = 1024.0f
    ).apply {
        path(
            fill = SolidColor(color), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(509.7f, 869.3f)
            curveToRelative(169.7f, 0.0f, 307.8f, -138.1f, 307.8f, -307.8f)
            verticalLineTo(59.1f)
            horizontalLineToRelative(-93.1f)
            verticalLineToRelative(502.4f)
            curveToRelative(0.0f, 118.4f, -96.3f, 214.7f, -214.7f, 214.7f)
            reflectiveCurveToRelative(-214.7f, -96.3f, -214.7f, -214.7f)
            verticalLineTo(59.1f)
            horizontalLineToRelative(-93.1f)
            verticalLineToRelative(502.4f)
            curveToRelative(0.0f, 169.7f, 138.1f, 307.8f, 307.8f, 307.8f)
            close()
            moveTo(44.3f, 929.0f)
            horizontalLineToRelative(930.9f)
            verticalLineToRelative(93.1f)
            horizontalLineTo(44.3f)
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
        Image(imageVector = SvgIcons.winEditU(), contentDescription = "")
    }
}
