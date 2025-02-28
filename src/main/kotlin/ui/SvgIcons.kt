package ui

import androidx.compose.ui.graphics.vector.ImageVector
import ui.icons.*

object SvgIcons

val SvgIcons.icons: List<ImageVector>
    get() {
        if (imageVectorList != null) {
            return imageVectorList!!
        }
        imageVectorList = listOf(
            CopyImage, DeleteImage, noImage(), Note, OpenImage, Watermelon,
            pin(), pinFill(), winBack(), winCheck(), winClose(), winDelete(),
            winDown(), winEditB(), winEditI(), winEditImage(), winEditList(),
            winEditS(), winEditU(), winExport(), winImage(), winIncrease(), winInfo(),
            winLeftAlign(), winList(), winMessage(), winMore(), winPin(), winPinFill(),
            winRawText(), winRefresh(), winSearch(), winSend(), winSetting(), winTag(), winText()
        )
        return imageVectorList!!
    }

private var imageVectorList: List<ImageVector>? = null