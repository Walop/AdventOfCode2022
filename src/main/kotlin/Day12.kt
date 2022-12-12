import java.io.InputStream

data class HeightMap(val start: List<Int>, val end: Int, val width: Int, val graph: List<List<Boolean>>)

class Day12 {
    companion object {
        private fun readMap(input: InputStream?, startTile: Char, endTile: Char): HeightMap {
            if (input == null) {
                throw Exception("Input missing")
            }

            var start = mutableListOf<Int>()
            var end = 0
            var width = 0

            val map = input.bufferedReader().readLines().flatMapIndexed { i, row ->
                width = row.length
                row.mapIndexed { j, tile ->
                    if (tile == startTile) {
                        start.add(i * width + j)
                    }
                    if (tile == endTile) {
                        end = i * width + j
                    }
                    when (tile) {
                        'S' -> {
                            'a'
                        }

                        'E' -> {
                            'z'
                        }

                        else -> {
                            tile
                        }
                    }
                }
            }

            val graph = MutableList(map.size) { MutableList(map.size) { false } }

            map.forEachIndexed { i, tile ->
                if (i % width + 1 < width) {
                    if (tile.code >= map[i + 1].code - 1) {
                        graph[i][i + 1] = true
                    }
                    if (tile.code <= map[i + 1].code + 1) {
                        graph[i + 1][i] = true
                    }
                }
                if (i + width < map.size) {
                    if (tile.code >= map[i + width].code - 1) {
                        graph[i][i + width] = true
                    }
                    if (tile.code <= map[i + width].code + 1) {
                        graph[i + width][i] = true
                    }
                }
            }

            return HeightMap(start, end, width, graph)
        }

        private fun findPath(graph: List<List<Boolean>>, width: Int, start: Int, end: Int): Int {
            val numOfVertices = graph.size

            val visitedAndDistance = List(numOfVertices) { mutableListOf(0, Int.MAX_VALUE) }

            visitedAndDistance[start][1] = 0

            fun nextToVisit(): Int {
                var v = -1
                for (i in 0 until numOfVertices) {
                    if (visitedAndDistance[i][0] == 0 && (v < 0 || visitedAndDistance[i][1] <= visitedAndDistance[v][1])) {
                        v = i
                    }
                }

                return v
            }

            repeat(numOfVertices) {
                val toVisit = nextToVisit()
                for (i in listOf(toVisit - 1, toVisit + 1, toVisit + width, toVisit - width)) {
                    if (i in 0 until numOfVertices && graph[toVisit][i] && visitedAndDistance[i][0] == 0) {
                        val newDistance = visitedAndDistance[toVisit][1] + 1
                        if (visitedAndDistance[i][1] > newDistance) {
                            visitedAndDistance[i][1] = newDistance
                            if (i == end) {
                                return newDistance
                            }
                        }
                    }
                    visitedAndDistance[toVisit][0] = 1
                }
            }

            return -1
        }

        fun process(input: InputStream?): Int {
            val heightMap = readMap(input, 'S', 'E')

            //heightMap.graph.forEach { println(it) }

            return findPath(heightMap.graph, heightMap.width, heightMap.start[0], heightMap.end)
        }

        fun process2(input: InputStream?): Int {
            val heightMap = readMap(input, 'a', 'E')

            var i = 0

            val lengths = heightMap.start.parallelStream().map {
                findPath(heightMap.graph, heightMap.width, it, heightMap.end)
            }.map {
                i++
                println("$i of ${heightMap.start.size} Done: $it")
                it
            }
                .filter { it > 0 }

            return lengths.min(Integer::compare).get()
        }
    }

}
