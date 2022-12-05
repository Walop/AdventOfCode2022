import java.io.InputStream
import java.util.*
import kotlin.collections.List

class Day5 {
    companion object {
        fun process(input: InputStream?): String {
            if (input == null) {
                throw Exception("Input missing")
            }

            val stacks = Array(9) { Stack<Char>() }

            input
                .bufferedReader()
                .useLines { s ->
                    val numRegex = """\d+""".toRegex()
                    s
                        .onEach {
                            if (it.firstOrNull() != 'm') it.forEachIndexed { index, c ->
                                if (c in 'A'..'Z') stacks[(index - 1) / 4].push(
                                    c
                                )
                            }
                        }
                        .onEach { if (it.isEmpty()) stacks.forEach { stack -> stack.reverse() } }
                        .forEach { line ->
                            if (line.firstOrNull() == 'm') {
                                val nums =
                                    numRegex.findAll(line)
                                        .map { it.value.toInt() }.toList()
                                repeat(nums[0]) {
                                    stacks[nums[2] - 1].push(stacks[nums[1] - 1].pop())
                                }
                            }
                        }
                }

            return String(stacks.mapNotNull { if (it.empty()) null else it.pop() }.toCharArray())
        }

        fun process2(input: InputStream?): String {
            if (input == null) {
                throw Exception("Input missing")
            }

            val stacks = Array(9) { Stack<Char>() }

            input
                .bufferedReader()
                .useLines { s ->
                    val numRegex = """\d+""".toRegex()
                    s
                        .onEach {
                            if (it.firstOrNull() != 'm') it.forEachIndexed { index, c ->
                                if (c in 'A'..'Z') stacks[(index - 1) / 4].push(
                                    c
                                )
                            }
                        }
                        .onEach { if (it.isEmpty()) stacks.forEach { stack -> stack.reverse() } }
                        .forEach { line ->
                            if (line.firstOrNull() == 'm') {
                                val nums =
                                    numRegex.findAll(line)
                                        .map { it.value.toInt() }.toList()

                                val temp = Stack<Char>()

                                repeat(nums[0]) {
                                    temp.push(stacks[nums[1] - 1].pop())
                                }

                                //println(temp)

                                repeat(nums[0]) {
                                    stacks[nums[2] - 1].push(temp.pop())
                                }
                            }
                        }
                }

            return String(stacks.mapNotNull { if (it.empty()) null else it.pop() }.toCharArray())
        }
    }

}
