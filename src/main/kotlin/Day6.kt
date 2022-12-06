import java.io.InputStream

class Day6 {
    companion object {
        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input.buffered().iterator().asSequence().windowed(4).takeWhile { it.toSet().size < 4 }.count() + 4
        }

        fun process2(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            return input.buffered().iterator().asSequence().windowed(14).takeWhile { it.toSet().size < 14 }.count() + 14
        }
    }

}
