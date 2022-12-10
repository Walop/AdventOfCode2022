import java.io.InputStream

class Day10 {
    companion object {
        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            var x = 1
            var cycle = 0
            var sum = 0

            fun addCycle() {
                cycle++
                if (cycle == 20 || (cycle + 20) % 40 == 0) {
                    println("$cycle ${cycle * x}")
                    sum += cycle * x
                }
            }

            input.bufferedReader()
                .useLines { lines ->
                    for (line in lines) {
                        val split = line.split(" ")
                        if (split[0] == "noop") {
                            addCycle()
                        } else {
                            addCycle()
                            addCycle()
                            x += split[1].toInt()
                        }
                    }
                }

            return sum
        }

        fun process2(input: InputStream?): String {
            if (input == null) {
                throw Exception("Input missing")
            }

            var x = 1
            var cycle = -1
            var image = ""

            fun addCycle() {
                cycle++
                image += if (cycle % 40 in x - 1..x + 1) {
                    "#"
                } else {
                    "."
                }
            }

            input.bufferedReader()
                .useLines { lines ->
                    for (line in lines) {
                        val split = line.split(" ")
                        if (split[0] == "noop") {
                            addCycle()
                        } else {
                            addCycle()
                            addCycle()
                            x += split[1].toInt()
                        }
                    }
                }

            image.chunked(40).forEach { println(it) }

            return image
        }
    }

}
