fun main() {
    fun part1(input: List<String>) = input.map {
        MyMove.byLetter(it[2])!!.getFightResult(OponentMove.byLetter(it[0])!!) + MyMove.byLetter(it[2])!!.value
    }.reduce { acc, result -> acc + result }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class OponentMove(val letter: Char) {
    ROCK('A'), PAPER('B'), SCISSORS('C');

    companion object {
        fun byLetter(letter: Char) = values().firstOrNull { it.letter == letter }
    }
}

enum class MyMove(val letter: Char, val value: Int) {
    ROCK('X', 1), PAPER('Y', 2), SCISSORS('Z', 3);

    fun getFightResult(oponentMove: OponentMove) : Int {
        return when (this) {
            ROCK -> when (oponentMove) {
                OponentMove.ROCK -> 3
                OponentMove.PAPER -> 0
                OponentMove.SCISSORS -> 6
            }
            PAPER -> when (oponentMove) {
                OponentMove.ROCK -> 6
                OponentMove.PAPER -> 3
                OponentMove.SCISSORS -> 0
            }
            SCISSORS -> when (oponentMove) {
                OponentMove.ROCK -> 0
                OponentMove.PAPER -> 6
                OponentMove.SCISSORS -> 3
            }
        }
    }

    companion object {
        fun byLetter(letter: Char) = values().firstOrNull { it.letter == letter }
    }
}