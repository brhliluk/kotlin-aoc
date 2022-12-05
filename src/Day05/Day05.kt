fun main() {
    val matcher = Regex("move (\\d.*) from (\\d) to (\\d)")
    //todo 2 cipher numbers
    val _dock = Array(9) { Array<Char?>(8) { null } }

    fun part1(input: List<String>) : CharArray {
        // Parse input
        for (i in 0..7) {
            for (j in 1..34 step 4) {
                with (input[i][j]) { if (this.isLetter()) _dock[j/4][7-i] = this }
            }
        }
        val dock = _dock.map { it.filterNotNull().toMutableList() }
        input.takeLast(input.size - 10).forEach {
            val match = matcher.matchEntire(it)!!
            val (count, from, to) = match.destructured
            repeat (count.toInt()) {
                dock[to.toInt()-1].add(dock[from.toInt()-1].removeLast())
            }
        }
        return dock.map { it.last() }.toCharArray()
    }

    fun part2(input: List<String>) = input.map {
        val oponentMove = OponentMove.byLetter(it[0])!!
        val myMove = FightResult.byLetter(it[2])!!.getRequiredMove(oponentMove)
        myMove.getFightResult(oponentMove) + myMove.value
    }.reduce { acc, result -> acc + result }

    val input = readInput("Day05")
    println(part1(input))
//    println(part2(input))
}