fun main() {
    val TOTAL_SPACE = 70000000
    val REQUIRED_SPACE = 30000000

    fun parseFs(input: List<String>): Folder {
        val root = Folder("/", null)
        var currentFolder = root
        input.forEach { line ->
            if (line.startsWith("$")) {
                val command = line.trimStart('$').trim().split(" ")
                when (command.first()) {
                    "cd" -> {
                        currentFolder = when (command.last()) {
                            ".." -> currentFolder.parent!!
                            "/" -> root
                            else -> currentFolder.contents.first { it is Folder && it.name == command.last() } as Folder
                        }
                    }
                    "ls" -> { /* Nothing? */
                    }
                    else -> error("Unknown command")
                }
            } else {
                val content = line.split(" ")
                when (content.first()) {
                    "dir" -> currentFolder.contents.add(Folder(content.last(), currentFolder))
                    else -> currentFolder.contents.add(File(content.last(), content.first().toInt()))
                }
            }
        }
        return root
    }

    fun part1(input: List<String>): Int {
        val root = parseFs(input)
        return root.folders.map { folder ->
            folder.getSmallerThan(setOf()).map { it.size }.sumOf { it }
        }.sumOf { it }
    }

    fun part2(input: List<String>): Int {
        val root = parseFs(input)
        val needsToBeFreed = REQUIRED_SPACE - (TOTAL_SPACE - root.size)
        return root.folders.map { folder ->
            val largeFolders = folder.getLargerThan(setOf(), needsToBeFreed).ifEmpty { return@map Int.MAX_VALUE }
            largeFolders.map { it.size }.minOf { it }
        }.minOf { it }
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

class Folder(override val name: String, val parent: Folder?, val contents: MutableList<FS> = mutableListOf()) : FS {
    override val size get() = contents.sumOf { it.size }
    val folders get() = contents.filterIsInstance<Folder>()

    fun getSmallerThan(smallerFolders: Set<Folder>, maxSize: Int = 100000): Set<Folder> {
        val smallFolders = mutableSetOf(smallerFolders)
        folders.forEach {
            smallFolders.add(it.getSmallerThan(setOf(), maxSize))
        }
        val result = smallerFolders union smallFolders.flatten().toSet()
        return if (size < maxSize) result + this@Folder else result
    }

    fun getLargerThan(largerFolders: Set<Folder>, minSize: Int): Set<Folder> {
        if (size < minSize) return largerFolders
        val largeFolders = mutableSetOf(largerFolders)
        folders.forEach {
            largeFolders.add(it.getLargerThan(setOf(), minSize))
        }
        val result = largerFolders union largeFolders.flatten().toSet()
        return result + this@Folder
    }
}

class File(override val name: String, override val size: Int) : FS

interface FS {
    val name: String
    val size: Int
}