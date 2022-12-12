fun main() {

    fun <T> List<List<T>>.getColumn(index: Int): List<T> {
        val result = mutableListOf<T>()
        forEachIndexed { i, elemList ->
            result.add(elemList[index])
        }
        return result
    }

    fun isVisible(row: List<Int>, index: Int): Boolean {
        if (index == 0 || index == row.size - 1) return true
        val leftMax = row.subList(0, index).max()
        val rightMax = row.subList(index + 1, row.size).max()
        val element = row[index]
        return element > rightMax || element > leftMax
    }

    fun isVisible(row: List<Int>, column: List<Int>, rowIdx: Int, columnIdx: Int): Boolean {
        return isVisible(row, rowIdx) || isVisible(column, columnIdx)
    }

    fun part1(input: List<String>): Int {
        val visibleTrees = mutableListOf<String>()
        val array = input.map { row ->
            row.toCharArray().map { it.digitToInt() }
        }

        array.forEachIndexed { i, trees ->
            trees.forEachIndexed { j, tree ->
                if (isVisible(trees, array.getColumn(j), j, i)) visibleTrees.add("$i$j")
            }
        }

        return visibleTrees.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
