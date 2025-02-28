package tool

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun currentTimeAsTimestamp(): Long {
    // return System.currentTimeMillis()
    return LocalDateTime.now()
        .atZone(ZoneId.systemDefault())
        .withZoneSameInstant(ZoneId.of("UTC"))
        .toInstant()
        .toEpochMilli()
}

fun formatTimestamp(epochMilli: Long, pattern: String = "yyyy/MM/dd HH:mm:ss"): String {
    return Instant.ofEpochMilli(epochMilli)
        .atZone(ZoneId.of("UTC"))
        .withZoneSameInstant(ZoneId.systemDefault())
        .toLocalDateTime()
        .format(DateTimeFormatter.ofPattern(pattern))
}