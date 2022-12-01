fun main() {
    fun part1(input: List<String>) = input.chunkBy { it.isEmpty() }.map {
            calloryList -> calloryList.map { it.toInt() }.reduce { acc, meal -> acc + meal }
        }.maxOrNull()

    fun part2(input: List<String>) = input.chunkBy { it.isEmpty() }.map {
            calloryList -> calloryList.map { it.toInt() }.reduce { acc, meal -> acc + meal }
    }.sortedDescending().take(3).reduce { acc, meal -> acc + meal }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

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
