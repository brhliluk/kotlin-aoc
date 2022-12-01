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
