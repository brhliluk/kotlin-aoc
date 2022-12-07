fun main() {
    fun parseFs(input: List<String>): Folder {
        val root = Folder("/", null)
        var currentFolder = root
        input.forEach { line ->
            if (line == "$ cd /") return@forEach
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
            folder.getSmallerThan(setOf())
                .map { it.size }
                .sumOf { it }
        }.sumOf { it }
    }

    val input = readInput("Day07")
    println(part1(input))
//    println(part2(input))
}

class Folder(override val name: String, val parent: Folder?, val contents: MutableList<FS> = mutableListOf()) : FS {
    override val size get() = contents.sumOf { it.size }
    val folders get() = contents.filterIsInstance<Folder>()

    fun getSmallerThan(smallerFolders: Set<Folder>, maxSize: Int = 100000): Set<Folder> {
        return if (size > maxSize) {
            val smallFolders = mutableSetOf(smallerFolders)
            folders.forEach {
                smallFolders.add(it.getSmallerThan(smallerFolders))
            }
            smallerFolders + smallFolders.flatten()
        } else {
            smallerFolders + this@Folder
        }
    }
}

class File(override val name: String, override val size: Int) : FS

interface FS {
    val name: String
    val size: Int
}