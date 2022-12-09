import java.io.InputStream
import kotlin.math.pow
import kotlin.math.sqrt

data class Vec2(
    var x: Int,
    var y: Int
) {
    operator fun plus(other: Vec2): Vec2 {
        return Vec2(x + other.x, y + other.y)
    }

    operator fun minus(other: Vec2): Vec2 {
        return Vec2(x - other.x, y - other.y)
    }

    fun length(): Float {
        return sqrt(x.toFloat().pow(2) + y.toFloat().pow(2))
    }
}

class Day9 {
    companion object {
        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            var head = Vec2(0, 0)
            var prevHead = Vec2(0, 0)
            var tail = Vec2(0, 0)
            val visited = mutableSetOf(tail)


            input.bufferedReader()
                .useLines { lines ->
                    lines.map { it.split(" ") }
                        .map { Pair(it[0], it[1].toInt()) }
                        .forEach { c ->
                            //println("${head.x},${head.y}")
                            val dir = when (c.first) {
                                "U" -> Vec2(0, 1)
                                "D" -> Vec2(0, -1)
                                "L" -> Vec2(-1, 0)
                                else -> Vec2(1, 0)
                            }
                            repeat(c.second) {
                                head += dir

                                if ((head - tail).length() > 1.5) {
                                    tail = prevHead
                                    visited.add(tail)
                                }
                                prevHead = head
                            }
                        }
                }

            return visited.size
        }

        fun process2(input: InputStream?): Int {
            TODO("Not yet implemented")
        }
    }

}
