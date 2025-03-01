import java.io.File
import java.util.zip.CRC32
import kotlin.time.measureTime


fun main(vararg args: String) {
    val mt = measureTime {
        val filePath =
            args.takeIf { it.isNotEmpty() }?.get(0)?.takeIf { it.isNotEmpty() } ?: "path_to_file/ip_addresses.txt"
        readBiggerBuffer(filePath)
    }
    println("""measureTime = $mt""")
}

private fun readBiggerBuffer(filePath: String) {
    var c = 0L
    val longBitSet = LongBitSet(256L * 256L * 256L * 256L)
    File(filePath).bufferedReader().useLines { lines ->
        lines.forEach { line ->
            if (isUnique(crc32Checksum(line.toByteArray()), longBitSet)) c++
        }
    }
    println("""counter = $c""")
}

fun isUnique(value: Long, bitSet: LongBitSet): Boolean {
    val unique = !(bitSet[value])
    if (!bitSet[value]) bitSet[value] = true
    return unique
}

private val crc = CRC32()
private fun crc32Checksum(data: ByteArray): Long {
    crc.reset()
    crc.update(data)
    return crc.value
}
