fun main() {
    val backpackItems = ('a'..'z' union 'A'..'Z')
    val itemValues = (1..52).toMutableList()
    val valuedItems = backpackItems.associateWith { itemValues.removeFirst() }
    var totalGroupValue = 0

    fun part1(input: List<String>) = input.map { backpack ->
        val firstHalf = backpack.take(backpack.length / 2).toSet()
        val secondHalf = backpack.takeLast(backpack.length / 2).toSet()
        valuedItems[(firstHalf intersect secondHalf).first()]!!
    }.sumOf { it }

    fun part2(input: List<String>): Int {
        input.chunked(3).map { group -> group.map { it.toSet() } }.forEach { group ->
            totalGroupValue += valuedItems[(group[0] intersect group[1] intersect group[2]).first()]!!
        }
        return totalGroupValue
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
