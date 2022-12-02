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

enum class FightResult(val letter: Char) {
    LOOSE('X'), DRAW('Y'), WIN('Z');

    fun getRequiredMove(oponentMove: OponentMove) = when (this) {
        WIN -> when (oponentMove) {
            OponentMove.ROCK -> MyMove.PAPER
            OponentMove.PAPER -> MyMove.SCISSORS
            OponentMove.SCISSORS -> MyMove.ROCK
        }
        DRAW -> when (oponentMove) {
            OponentMove.ROCK -> MyMove.ROCK
            OponentMove.PAPER -> MyMove.PAPER
            OponentMove.SCISSORS -> MyMove.SCISSORS
        }
        LOOSE -> when (oponentMove) {
            OponentMove.ROCK -> MyMove.SCISSORS
            OponentMove.PAPER -> MyMove.ROCK
            OponentMove.SCISSORS -> MyMove.PAPER
        }
    }

    companion object {
        fun byLetter(letter: Char) = values().firstOrNull { it.letter == letter }
    }
}