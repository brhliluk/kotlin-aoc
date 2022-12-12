fun main() {

    fun List<Int>.takeWhileInclusive(pred: (Int) -> Boolean): List<Int> {
        var shouldContinue = true
        return takeWhile {
            val result = shouldContinue
            shouldContinue = pred(it)
            result
        }
    }

    fun List<Int>.takeLastWhileInclusive(pred: (Int) -> Boolean): List<Int> {
        var shouldContinue = true
        return takeLastWhile {
            val result = shouldContinue
            shouldContinue = pred(it)
            result
        }
    }

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

    fun isVisible(row: List<Int>, column: List<Int>, rowIdx: Int, columnIdx: Int) = isVisible(row, rowIdx) || isVisible(column, columnIdx)

    fun scenicScore(row: List<Int>, index: Int): Int {
        val tree = row[index]
        val leftTrees = if (index == 0) listOf() else row.subList(0, index)
        val rightTrees = if (index == row.size - 1) listOf() else row.subList(index + 1, row.size)
        val leftVisible = if (leftTrees.isEmpty()) leftTrees else leftTrees.takeLastWhileInclusive { it < tree }
        val rightVisible = if (rightTrees.isEmpty()) rightTrees else rightTrees.takeWhileInclusive { it < tree }
        return leftVisible.size * rightVisible.size
    }

    fun scenicScore(row: List<Int>, column: List<Int>, rowIdx: Int, columnIdx: Int) = scenicScore(row, rowIdx) * scenicScore(column, columnIdx)

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
        val scores = mutableListOf<Int>()
        val array = input.map { row ->
            row.toCharArray().map { it.digitToInt() }
        }
        array.forEachIndexed { i, trees ->
            trees.forEachIndexed { j, tree ->
                scores.add(scenicScore(trees, array.getColumn(j), j, i))
            }
        }
        return scores.max()
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
