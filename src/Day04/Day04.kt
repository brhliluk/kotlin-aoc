fun main() {
    val matcher = Regex("(\\d.*)-(\\d.*),(\\d.*)-(\\d.*)")

    fun part1(input: List<String>) = input.count { sections ->
        val matchedResult = matcher.matchEntire(sections)!!
        val (firstElfStart, firstElfEnd, secondElfStart, secondElfEnd) = matchedResult.destructured
        val firstElf = firstElfStart.toInt()..firstElfEnd.toInt()
        val secondElf = secondElfStart.toInt()..secondElfEnd.toInt()
        firstElf.all { it in secondElf } || secondElf.all { it in firstElf }
    }

    fun part2(input: List<String>) = input.count { sections ->
        val matchedResult = matcher.matchEntire(sections)!!
        val (firstElfStart, firstElfEnd, secondElfStart, secondElfEnd) = matchedResult.destructured
        val firstElf = firstElfStart.toInt()..firstElfEnd.toInt()
        val secondElf = secondElfStart.toInt()..secondElfEnd.toInt()
        (firstElf intersect secondElf).isNotEmpty()
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}