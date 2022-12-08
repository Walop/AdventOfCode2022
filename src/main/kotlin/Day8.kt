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

        fun process2(input: InputStream?): Int {
            TODO("Not yet implemented")
        }
    }

}
