import java.io.InputStream

class Day14 {
    companion object {
        private fun getInitial(input: InputStream?): MutableSet<Vec2> {
            if (input == null) {
                throw Exception("Input missing")
            }

            val coordRegex = "[\\d,]+".toRegex()

            return input.bufferedReader().useLines { lines ->
                lines
                    .map { line ->
                        coordRegex.findAll(line).map { it.value }
                    }
                    .map { line ->
                        line.map { coord -> coord.split(",").map { it.toInt() } }
                    }
                    .flatMap { line ->
                        line.windowed(2).flatMap { coord ->
                            // println(coord)
                            if (coord[0][0] == coord[1][0]) {
                                val range =
                                    if (coord[0][1] < coord[1][1])
                                        coord[0][1]..coord[1][1]
                                    else
                                        coord[1][1]..coord[0][1]
                                (range).map {
                                    Vec2(coord[0][0], it)
                                }
                            } else {
                                val range =
                                    if (coord[0][0] < coord[1][0])
                                        coord[0][0]..coord[1][0]
                                    else
                                        coord[1][0]..coord[0][0]
                                (range).map {
                                    Vec2(it, coord[0][1])
                                }
                            }
                        }
                    }
                    .toMutableSet()
            }
        }

        fun process(input: InputStream?): Int {
            val filled = getInitial(input)

            // println(filled)

//            for (y in filled.minBy { it.y }.y..filled.maxBy { it.y }.y) {
//                for (x in filled.minBy { it.x }.x..filled.maxBy { it.x }.x) {
//                    if (filled.contains(Vec2(x, y))) {
//                        print("#")
//                    } else {
//                        print(".")
//                    }
//                }
//                println()
//            }

            val bottom = filled.maxBy { it.y }.y

            var done = false
            var rounds = 0

            while (!done) {
                rounds++
                var grain = Vec2(500, 0)
                var stopped = false

                while (grain.y < bottom && !stopped) {
                    if (!filled.contains(Vec2(grain.x, grain.y + 1))) {
                        grain.y += 1
                    } else if (!filled.contains(Vec2(grain.x - 1, grain.y + 1))) {
                        grain.x -= 1
                        grain.y += 1
                    } else if (!filled.contains(Vec2(grain.x + 1, grain.y + 1))) {
                        grain.x += 1
                        grain.y += 1
                    } else {
                        filled.add(grain)
                        stopped = true
                    }
                    if (grain.y == bottom) {
                        done = true
                    }
                }
            }

            return rounds - 1
        }

        fun process2(input: InputStream?): Int {
            val filled = getInitial(input)

            val bottom = filled.maxBy { it.y }.y + 1

            var rounds = 0

            while (!filled.contains(Vec2(500, 0))) {
                rounds++
                var grain = Vec2(500, 0)
                var stopped = false

                while (!stopped) {
                    if (grain.y == bottom) {
                        filled.add(grain)
                        stopped = true
                    } else if (!filled.contains(Vec2(grain.x, grain.y + 1))) {
                        grain.y += 1
                    } else if (!filled.contains(Vec2(grain.x - 1, grain.y + 1))) {
                        grain.x -= 1
                        grain.y += 1
                    } else if (!filled.contains(Vec2(grain.x + 1, grain.y + 1))) {
                        grain.x += 1
                        grain.y += 1
                    } else {
                        filled.add(grain)
                        stopped = true
                    }
                }
            }

//            println(filled)
//
//            for (y in filled.minBy { it.y }.y..filled.maxBy { it.y }.y) {
//                for (x in filled.minBy { it.x }.x..filled.maxBy { it.x }.x) {
//                    if (filled.contains(Vec2(x, y))) {
//                        print("#")
//                    } else {
//                        print(".")
//                    }
//                }
//                println()
//            }

            return rounds
        }
    }

}
