import java.io.InputStream

data class HeightMap(val start: Int, val end: Int, val graph: List<List<Boolean>>)

class Day12 {
    companion object {
        private fun readMap(input: InputStream?): HeightMap {
            if (input == null) {
                throw Exception("Input missing")
            }

            var start = 0
            var end = 0
            var width = 0

            val map = input.bufferedReader().readLines().flatMapIndexed { i, row ->
                width = row.length
                row.mapIndexed { j, tile ->
                    when (tile) {
                        'S' -> {
                            start = i * width + j
                            'a'
                        }

                        'E' -> {
                            end = i * width + j
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

            return HeightMap(start, end, graph)
        }


        fun process(input: InputStream?): Int {
            val heightMap = readMap(input)

            //heightMap.graph.forEach { println(it) }

            val numOfVertices = heightMap.graph.size

            val visitedAndDistance = List(numOfVertices) { mutableListOf(0, Int.MAX_VALUE) }

            visitedAndDistance[heightMap.start][1] = 0

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
                for (i in 0 until numOfVertices) {
                    if (heightMap.graph[toVisit][i] && visitedAndDistance[i][0] == 0) {
                        val newDistance = visitedAndDistance[toVisit][1] + 1
                        if (visitedAndDistance[i][1] > newDistance) {
                            visitedAndDistance[i][1] = newDistance
                            if (i == heightMap.end) {
                                return newDistance
                            }
                        }
                    }
                    visitedAndDistance[toVisit][0] = 1
                }
            }

            return -1
        }

        fun process2(input: InputStream?): Int {
            TODO("Not yet implemented")
        }
    }

}
