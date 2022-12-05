fun main() {
    fun part1(input: List<String>) = input.map {
        MyMove.byLetter(it[2])!!.getFightResult(OponentMove.byLetter(it[0])!!) + MyMove.byLetter(it[2])!!.value
    }.reduce { acc, result -> acc + result }

    fun part2(input: List<String>) = input.map {
        val oponentMove = OponentMove.byLetter(it[0])!!
        val myMove = FightResult.byLetter(it[2])!!.getRequiredMove(oponentMove)
        myMove.getFightResult(oponentMove) + myMove.value
    }.reduce { acc, result -> acc + result }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}