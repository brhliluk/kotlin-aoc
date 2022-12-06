fun main() {
    fun getFirstNDistinct(input: String, n: Int): Int {
        val buffer = mutableListOf<Char>()
        input.forEachIndexed { index, s ->
            buffer.add(s)
            if (index < n - 1) return@forEachIndexed
            if (buffer.distinct().size == n)
                return index + 1
            else
                buffer.removeFirst()
        }
        return -1
    }

    fun part1(input: List<String>) = getFirstNDistinct(input[0], 4)

    fun part2(input: List<String>) = getFirstNDistinct(input[0], 14)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}