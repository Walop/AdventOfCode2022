import java.io.InputStream
import kotlin.math.sqrt

class Day8 {
    companion object {
        private fun readTreeMap(input: InputStream?): String {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input.reader().readLines().joinToString("")
        }

        fun process(input: InputStream?): Int {
            val treeMap = readTreeMap(input)
            val side = sqrt(treeMap.length.toDouble()).toInt()

            return treeMap.mapIndexed { index, c ->
                val x = index % side
                val y = index / side

                if (x == 0 || x + 1 == side || y == 0 || y + 1 == side) {
                    print(1)
                    1
                } else {
                    if ((y * side until index).any { treeMap[it] >= c } &&
                        (index + 1 until (y + 1) * side).any { treeMap[it] >= c } &&
                        (x until index step side).any { treeMap[it] >= c } &&
                        ((index + side)..treeMap.length step side).any { treeMap[it] >= c }) {
                        print(0)
                        0
                    } else {
                        print(1)
                        1
                    }
                }
            }
                .sum()
        }

        private fun countVisibility(treeMap: String, start: Int, end: Int, step: Int, height: Char): Int {
            var index = start + step
            var sum = 0
            if (step > 0) {
                while (index < treeMap.length && index < end) {
                    sum++
                    if (treeMap[index] >= height) break
                    index += step
                }
            } else {
                while (index >= 0 && index > end) {
                    sum++
                    if (treeMap[index] >= height) break
                    index += step
                }
            }

            return sum
        }

        fun process2(input: InputStream?): Int {
            val treeMap = readTreeMap(input)
            val side = sqrt(treeMap.length.toDouble()).toInt()

            return treeMap.mapIndexed { index, c -> Pair(index, c) }.filter { pair ->
                val x = pair.first % side
                val y = pair.first / side

                if (x == 0 || x + 1 == side || y == 0 || y + 1 == side) {
                    true
                } else {
                    !((y * side until pair.first).any { treeMap[it] >= pair.second } &&
                            (pair.first + 1 until (y + 1) * side).any { treeMap[it] >= pair.second } &&
                            (x until pair.first step side).any { treeMap[it] >= pair.second } &&
                            ((pair.first + side)..treeMap.length step side).any { treeMap[it] >= pair.second })
                }
            }.maxOf { pair ->
                val x = pair.first % side
                val y = pair.first / side

                if (x == 0 || x + 1 == side || y == 0 || y + 1 == side) {
                    0
                } else {
                    val left = countVisibility(treeMap, pair.first, y * side - 1, -1, pair.second)
                    val right = countVisibility(treeMap, pair.first, (y + 1) * side, 1, pair.second)
                    val up = countVisibility(treeMap, pair.first, x - 1, -side, pair.second)
                    val down = countVisibility(treeMap, pair.first, treeMap.length, side, pair.second)

//                    val left = (pair.first - 1..y * side).takeWhile { treeMap[it] < pair.second }.count() + 1
//                    val right = (pair.first + 1 until (y + 1) * side).takeWhile { treeMap[it] < pair.second }
//                        .count() + 1
//                    val up = (pair.first - side..x step side).takeWhile { treeMap[it] < pair.second }.count() + 1
//                    val down = (pair.first + side..treeMap.length step side).takeWhile { treeMap[it] < pair.second }
//                        .count() + 1

                    println("${x},${y}: ${left * right * up * down}")

                    left * right * up * down
                }
            }
        }
    }

}
