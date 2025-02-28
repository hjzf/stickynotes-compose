package tool

import java.io.File

fun File.readKeyValues(): Map<String, String> {
    if (!this.exists() || !this.canRead()) {
        return emptyMap()
    }
    val lines = this.readLines()
    val count = lines.size / 2
    val kv = HashMap<String, String>(count)
    for (i in 0..<count) {
        kv[lines[i * 2].unescapeJava()] = lines[i * 2 + 1].unescapeJava()
    }
    return kv
}

fun File.writeKeyValues(kv: Map<String, String>) {
    if (!this.exists()) {
        this.parentFile.mkdirs()
    }
    val lines = ArrayList<String>(kv.size * 2)
    for (entry in kv.entries) {
        lines.add(entry.key.escapeJava())
        lines.add(entry.value.escapeJava())
    }
    this.writeText(lines.joinToString("\n"))
}