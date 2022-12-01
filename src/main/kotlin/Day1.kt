import java.io.InputStream

class Day1 {
    companion object {
        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            var i = 0
            val sums = emptyList<Int>().toMutableList()
            sums.add(0)

            input.reader().forEachLine {
                if (it.isEmpty()) {
                    i++
                    sums.add(0)
                } else {
                    sums[i] += it.toInt()
                }
            }

            return sums.max()
        }

        fun process2(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            var i = 0
            val sums = emptyList<Int>().toMutableList()
            sums.add(0)

            input.reader().forEachLine {
                if (it.isEmpty()) {
                    i++
                    sums.add(0)
                } else {
                    sums[i] += it.toInt()
                }
            }

            return sums.sortedDescending().take(3).sum()
        }
    }
}
