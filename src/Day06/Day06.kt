fun main() {
    fun part1(input: List<String>): Int {
        val buffer = mutableListOf<Char>()
        input[0].forEachIndexed { index, s ->
            buffer.add(s)
            if (index < 3) return@forEachIndexed
            if (buffer.distinct().size == 4)
                return index + 1
            else
                buffer.removeFirst()
        }
        return -1
    }

    fun part2(input: List<String>) = input.map {
        val oponentMove = OponentMove.byLetter(it[0])!!
        val myMove = FightResult.byLetter(it[2])!!.getRequiredMove(oponentMove)
        myMove.getFightResult(oponentMove) + myMove.value
    }.reduce { acc, result -> acc + result }

    val input = readInput("Day06")
    println(part1(input))
//    println(part2(input))
}