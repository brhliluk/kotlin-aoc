fun main() {
    fun part1(input: List<String>): Int {
        var register = 1
        var cycle = 0
        val signalStrengths = mutableListOf<Int>()
        val importantCycles = listOf(20, 60, 100, 140, 180, 220)
        input.forEach { instruction ->
            when {
                instruction.startsWith("noop") -> {
                    cycle += 1
                    if (cycle in importantCycles) signalStrengths.add(cycle * register)
                }
                instruction.startsWith("addx") -> {
                    cycle += 2
                    if (cycle - 1 in importantCycles) signalStrengths.add((cycle - 1) * register)
                    if (cycle in importantCycles) signalStrengths.add(cycle * register)
                    register += instruction.trimStart('a', 'd', 'x').trim().toInt()
                }
                else -> error("Unknown instruction")
            }
        }
        return signalStrengths.sum()
    }

    fun part2(input: List<String>): Int {
        var register = 1
        var cycle = 0
        val signalStrengths = mutableListOf<Int>()
        val importantCycles = listOf(20, 60, 100, 140, 180, 220)
        input.forEach { instruction ->
            when {
                instruction.startsWith("noop") -> {
                    cycle += 1
                    if (cycle in importantCycles) signalStrengths.add(cycle * register)
                }
                instruction.startsWith("addx") -> {
                    cycle += 2
                    if (cycle - 1 in importantCycles) signalStrengths.add((cycle - 1) * register)
                    if (cycle in importantCycles) signalStrengths.add(cycle * register)
                    register += instruction.trimStart('a', 'd', 'x').trim().toInt()
                }
                else -> error("Unknown instruction")
            }
        }
        return signalStrengths.sum()
    }

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}