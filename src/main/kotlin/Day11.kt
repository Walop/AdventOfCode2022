import java.io.InputStream

data class Monkey(
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val test: Long,
    val trueReceiver: Int,
    val falseReceiver: Int,
    var inspectionCount: Long = 0
)

class Day11 {
    companion object {
        private fun createOperation(operator: String, other: Long?): (Long) -> Long {
            return when (operator) {
                "+" -> { old: Long -> old + (other ?: old) }
                else -> { old: Long -> old * (other ?: old) }
            }
        }

        private fun privateProcess(input: InputStream?, rounds: Int, worryDivider: Long): Long {
            if (input == null) {
                throw Exception("Input missing")
            }

            val numRegex = "\\d+".toRegex()
            val operatorRegex = "[+*]".toRegex()

            val monkeys = input.bufferedReader().useLines { lines ->
                lines.chunked(7)
                    .map { monkeyDesc ->
                        val items = numRegex.findAll(monkeyDesc[1]).map { it.value.toLong() }.toMutableList()

                        //println(monkeyDesc)

                        Monkey(
                            items,
                            createOperation(
                                operatorRegex.find(monkeyDesc[2])!!.value,
                                numRegex.find(monkeyDesc[2])?.value?.toLong()
                            ),
                            numRegex.find(monkeyDesc[3])!!.value.toLong(),
                            numRegex.find(monkeyDesc[4])!!.value.toInt(),
                            numRegex.find(monkeyDesc[5])!!.value.toInt(),
                        )
                    }.toList()
            }

            val mod = monkeys.map { it.test }.fold(1L) { acc, it -> acc * it }

            for (i in 1..rounds) {
                for (monkey in monkeys) {
                    monkey.items.map {
                        monkey.operation(it) / worryDivider
                    }.forEach {
                        monkey.inspectionCount++
                        val reveiver =
                            if ((it) % monkey.test == 0L) monkey.trueReceiver else monkey.falseReceiver
                        val converted = it % mod
                        monkeys[reveiver].items.add(converted)
                    }
                    monkey.items.clear()
                }

//                if (i == 1 || i % 1000 == 0) {
//                    monkeys.forEachIndexed { index, it -> println("$index: ${it.items}, ${it.inspectionCount}") }
//                    println()
//                }
            }

            return monkeys.sortedByDescending { it.inspectionCount }.take(2).map { it.inspectionCount }
                .fold(1L) { acc, count -> acc * count }
        }

        fun process(input: InputStream?): Long {
            return privateProcess(input, 20, 3L)
        }

        fun process2(input: InputStream?): Long {
            return privateProcess(input, 10_000, 1L)
        }
    }

}
