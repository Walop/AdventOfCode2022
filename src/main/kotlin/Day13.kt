import java.io.InputStream
import java.util.*

data class NumOrArray(val num: Int?, var array: MutableList<NumOrArray>?)

class Day13 {
    companion object {
        private fun buildPacket(str: String): NumOrArray {
            val packet = NumOrArray(null, mutableListOf())
            val activeArrays = Stack<NumOrArray>()
            activeArrays.push(packet)

            var numStr = ""

            for (c in str) {
                when (c) {
                    '[' -> {
                        val newArray = NumOrArray(null, mutableListOf<NumOrArray>())
                        val prevArray = activeArrays.pop()
                        prevArray.array!!.add(newArray)
                        activeArrays.push(prevArray)
                        activeArrays.push(newArray)
                    }

                    ']' -> {
                        val currentArray = activeArrays.pop()
                        if (numStr.isNotEmpty()) {
                            currentArray.array!!.add(NumOrArray(numStr.toInt(), null))
                            numStr = ""
                        }
                    }

                    ',' -> {
                        val currentArray = activeArrays.pop()
                        if (numStr.isNotEmpty()) {
                            currentArray.array!!.add(NumOrArray(numStr.toInt(), null))
                            numStr = ""
                        }
                        activeArrays.push(currentArray)
                    }

                    else -> numStr += c
                }
            }

            return activeArrays.pop()
        }

        private fun compare(left: NumOrArray, right: NumOrArray): Int {
            for (i in 0 until left.array!!.size) {
                if (i > right.array!!.size - 1) {
                    //println("Right ran out of elements")
                    return -1
                } else if (left.array!![i].num != null && right.array!![i].num != null) {
                    if (left.array!![i].num!! > right.array!![i].num!!) {
                        //println("Left bigger")
                        return -1
                    }
                    if (left.array!![i].num!! < right.array!![i].num!!) {
                        //println("Left smaller")
                        return 1
                    }
                } else if (left.array!![i].num != null && right.array!![i].num == null) {
                    //println("Converting left to array")
                    val res = compare(NumOrArray(null, mutableListOf(left.array!![i])), right.array!![i])
                    if (res != 0) {
                        return res
                    }
                } else if (left.array!![i].num == null && right.array!![i].num != null) {
                    //println("Converting right to array")
                    val res = compare(left.array!![i], NumOrArray(null, mutableListOf(right.array!![i])))
                    if (res != 0) {
                        return res
                    }
                } else {
                    val res = compare(left.array!![i], right.array!![i])
                    if (res != 0) {
                        return res
                    }
                }
            }
            if (left.array!!.size == right.array!!.size) {
                //println("Left and right equal")
                return 0
            }
            //println("Left ran out of elements")
            return 1
        }

        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input.bufferedReader().useLines { lines ->
                lines.filter { it.isNotEmpty() }.map { buildPacket(it) }.chunked(2).map {
                    compare(it[0], it[1])
                }
                    .onEachIndexed { index, it -> println("$index: $it") }
                    .mapIndexed { i, it -> if (it > -1) i + 1 else 0 }
                    .sum()
            }
        }

        fun process2(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input.bufferedReader().useLines { lines ->
                (sequenceOf("[[2]]", "[[6]]") +
                        lines).filter { it.isNotEmpty() }.map { buildPacket(it) }
                    .sortedWith { left, right ->
                        -compare(
                            left,
                            right
                        )
                    }
                    .onEachIndexed { i, it -> println("$i: $it") }
                    .mapIndexedNotNull { i, it ->
                        val correctArray = it.array?.firstOrNull()?.array?.firstOrNull()?.array
                        if (it.array?.size != 1) {
                            null
                        } else if (it.array?.firstOrNull()?.array?.size != 1) {
                            null
                        } else if (correctArray?.size == 1 && correctArray.first().num in listOf(2, 6)) {
                            i + 1
                        } else {
                            null
                        }
                    }.fold(1) { acc, i -> acc * i }

            }
        }
    }

}
