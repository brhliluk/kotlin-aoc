fun main() {
    val matcher = Regex("move (\\d.*) from (\\d) to (\\d)")

    fun parseInput(input: List<String>, inputLinesLen: Int = 7, step: Int = 4, crateTowers: Int = 9): List<MutableList<Char>> {
        val dock = Array(crateTowers) { Array<Char?>(inputLinesLen + 1) { null } }
        for (i in 0..inputLinesLen) {
            for (j in 1 until inputLinesLen * step + crateTowers step step) {
                with(input[i][j]) { if (this.isLetter()) dock[j / step][inputLinesLen - i] = this }
            }
        }
        return dock.map { it.filterNotNull().toMutableList() }
    }

    fun part1(input: List<String>): CharArray {
        val dock = parseInput(input)
        input.takeLast(input.size - 10).forEach {
            val (count, from, to) = matcher.matchEntire(it)!!.destructured
            repeat(count.toInt()) {
                dock[to.toInt() - 1].add(dock[from.toInt() - 1].removeLast())
            }
        }
        return dock.map { it.last() }.toCharArray()
    }

    fun part2(input: List<String>): CharArray {
        val dock = parseInput(input)
        input.takeLast(input.size - 10).forEach {
            val (count, from, to) = matcher.matchEntire(it)!!.destructured
            dock[to.toInt() - 1] += dock[from.toInt() - 1].takeLast(count.toInt())
            repeat(count.toInt()) {
                dock[from.toInt() - 1].removeLast()
            }
        }
        return dock.mapNotNull { it.lastOrNull() }.toCharArray()
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}