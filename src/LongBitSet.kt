class LongBitSet(size: Long)  {
    private val words = LongArray(((size + 63) / 64).toInt())

    operator fun get(index: Long): Boolean {
        val wordIndex = (index / 64).toInt()
        val bitIndex = (index % 64).toInt()
        return (words[wordIndex] and (1L shl bitIndex)) != 0L
    }

    operator fun set(index: Long, value: Boolean) {
        val wordIndex = (index / 64).toInt()
        val bitIndex = (index % 64).toInt()
        if (value) {
            words[wordIndex] = words[wordIndex] or (1L shl bitIndex)
        } else {
            words[wordIndex] = words[wordIndex] and (1L shl bitIndex).inv()
        }
    }
}