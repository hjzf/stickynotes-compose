package tool

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.concurrent.ConcurrentHashMap

object FormatterCache {
    private val _cache = ConcurrentHashMap<String, DateTimeFormatter>()
    fun get(pattern: String): DateTimeFormatter {
        return _cache.getOrPut(pattern) { DateTimeFormatter.ofPattern(pattern) }
    }
}

fun currentTimestamp(): Long = System.currentTimeMillis()

fun formatTimestamp(epochMilli: Long, pattern: String = "yyyy/MM/dd HH:mm:ss"): String {
    return Instant.ofEpochMilli(epochMilli).atZone(ZoneId.systemDefault()).format(FormatterCache.get(pattern))
}