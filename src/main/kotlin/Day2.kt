import java.io.InputStream

class Day2 {
    companion object {
        private val points = mapOf("A" to 1, "X" to 1, "B" to 2, "Y" to 2, "C" to 3, "Z" to 3)

        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input
                .bufferedReader()
                .useLines { ln ->
                    ln
                        .map { it.split(" ") }
                        .map { it.map { s -> points[s] ?: 0 } }
                        .sumOf {
                            val win = if (it[0] == 3 && it[1] == 1) {
                                6
                            } else if (it[0] == 1 && it[1] == 3) {
                                0
                            } else if (it[0] < it[1]) {
                                6
                            } else if (it[0] == it[1]) {
                                3
                            } else {
                                0
                            }
                            //println("$it, ${it[1]+win}")
                            it[1] + win
                        }
                }

        }

        fun process2(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input
                .bufferedReader()
                .useLines { ln ->
                    ln
                        .map { it.split(" ") }
                        .map { it.map { s -> points[s] ?: 0 } }
                        .sumOf {
                            if (it[1] == 1) {
                                (it[0] + 1) % 3 + 1
                            } else if (it[1] == 2) {
                                3 + it[0]
                            } else {
                                6 + (it[0] + 3) % 3 + 1
                            }
                        }
                }

        }
    }

}
