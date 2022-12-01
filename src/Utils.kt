import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun <T> List<T>.chunkBy(predicate: (T) -> Boolean): Sequence<List<T>> = sequence {
    val currentGroup = mutableListOf<T>()
    for (element in this@chunkBy) {
        if (predicate(element)) {
            yield(currentGroup.toList())
            currentGroup.clear()
        } else currentGroup += element
    }
    yield(currentGroup)
}