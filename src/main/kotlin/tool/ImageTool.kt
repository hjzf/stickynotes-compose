package tool

private val imageFileNameRegex = Regex("^(.+?)\\.(jpg|jpeg|png|gif|bmp|webp)$", RegexOption.IGNORE_CASE)
fun String.isImageName() = imageFileNameRegex.matches(this)