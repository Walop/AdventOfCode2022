import java.io.InputStream

class Day4 {
    companion object {
        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input
                .bufferedReader()
                .useLines { ln ->
                    ln
                        .flatMap { it.split(',', '-') }
                        .map { it.toInt() }
                        .chunked(4)
                        .count { (it[0] <= it[2] && it[1] >= it[3]) || (it[2] <= it[0] && it[3] >= it[1]) }
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
                        .flatMap { it.split(',', '-') }
                        .map { it.toInt() }
                        .chunked(4)
                        .count {
                            (it[0] <= it[2] && it[1] >= it[2]) ||
                                    (it[0] <= it[3] && it[1] >= it[3]) ||
                                    (it[2] <= it[0] && it[3] >= it[0]) ||
                                    (it[2] <= it[1] && it[3] >= it[1])
                        }
                }
        }
    }

}
