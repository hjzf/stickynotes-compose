package tool

import org.apache.commons.text.StringEscapeUtils

fun String.clearAllHtmlTags() = this.replace(Regex("(<[^<]*?>)|(<\\s*?/[^<]*?>)|(<[^<]*?/\\s*?>)"), "")
fun String.escapeHtml4() = StringEscapeUtils.escapeHtml4(this) ?: ""
fun String.unescapeHtml4() = StringEscapeUtils.unescapeHtml4(this) ?: ""
fun String.escapeJava() = StringEscapeUtils.escapeJava(this) ?: ""
fun String.unescapeJava() = StringEscapeUtils.unescapeJava(this) ?: ""