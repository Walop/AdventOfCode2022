import java.io.InputStream

class Day3 {
    companion object {
        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input
                .bufferedReader()
                .useLines { ln -> ln
                    .map {
                        Pair(
                            it.subSequence(0, it.length / 2).toSet(),
                            it.subSequence(it.length / 2, it.length).toSet()
                        )
                    }
                    //.map { println(it); it }
                    .flatMap { it.first.intersect(it.second) }
                    //.map { println(it); it }
                    .sumOf { if (it in 'a'..'z') it.code - 96 else it.code - 38 }
                }

        }

        fun process2(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input
                .bufferedReader()
                .useLines { ln -> ln
                    .chunked(3)
                    .map { it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()).first() }
                    .sumOf { if (it in 'a'..'z') it.code - 96 else it.code - 38 }
                }

        }
    }

}
