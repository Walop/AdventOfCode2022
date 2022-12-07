import java.io.InputStream

interface IFile {
    val name: String
    val size: Int
}

data class File(override val name: String, override val size: Int) : IFile
data class Directory(
    override val name: String,
    override var size: Int,
    val children: MutableList<IFile>,
    val parent: Directory?,

    ) : IFile

class Day7 {
    companion object {
        private fun sumLessThan100k(dir: Directory): Int {
            val returnSize = if (dir.size <= 100_000) dir.size else 0
            val subDirs = dir.children.filterIsInstance<Directory>()
            if (subDirs.isNotEmpty()) {
                return returnSize + subDirs.sumOf { sumLessThan100k(it) }
            }
            return returnSize
        }

        private fun dirsMoreThan(dir: Directory, min: Int): List<Int> {
            val returnList = if (dir.size >= min) listOf(dir.size) else listOf()
            val subDirs = dir.children.filterIsInstance<Directory>()
            if (subDirs.isNotEmpty()) {
                return returnList + subDirs.flatMap { dirsMoreThan(it, min) }
            }
            return returnList
        }

        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input
                .bufferedReader()
                .useLines { lines ->
                    val root = Directory("/", 0, mutableListOf(), null)
                    var current = root

                    for (line in lines.drop(1)) {
                        when (line.take(4)) {
                            "$ cd" -> {
                                val dir = line.drop(5)
                                if (dir == "..") {
                                    current.size = current.children.sumOf { it.size }
                                    current = current.parent!!
                                } else {
                                    current = current.children.find { it.name == line.drop(5) } as Directory
                                }
                            }

                            "$ ls" -> continue
                            "dir " -> current.children.add(Directory(line.drop(4), 0, mutableListOf(), current))
                            else -> {
                                val split = line.split(" ")
                                current.children.add(File(split[1], split[0].toInt()))
                            }
                        }
                    }
                    current.size = current.children.sumOf { it.size }
                    root.size = root.children.sumOf { it.size }

                    sumLessThan100k(root)
                }
        }

        fun process2(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input
                .bufferedReader()
                .useLines { lines ->
                    val root = Directory("/", 0, mutableListOf(), null)
                    var current = root

                    for (line in lines.drop(1)) {
                        when (line.take(4)) {
                            "$ cd" -> {
                                val dir = line.drop(5)
                                if (dir == "..") {
                                    current.size = current.children.sumOf { it.size }
                                    current = current.parent!!
                                } else {
                                    current = current.children.find { it.name == line.drop(5) } as Directory
                                }
                            }

                            "$ ls" -> continue
                            "dir " -> current.children.add(Directory(line.drop(4), 0, mutableListOf(), current))
                            else -> {
                                val split = line.split(" ")
                                current.children.add(File(split[1], split[0].toInt()))
                            }
                        }
                    }
                    while (current.parent != null) {
                        current.size = current.children.sumOf { it.size }
                        current = current.parent!!
                    }

                    root.size = root.children.sumOf { it.size }

                    val toFree = 70_000_000 - 30_000_000 - root.size
                    dirsMoreThan(root, -toFree).min()
                }
        }
    }

}
