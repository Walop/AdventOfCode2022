import java.io.InputStream

data class Monkey(
    val items: MutableList<Int>,
    val operation: (Int) -> Int,
    val test: Int,
    val trueReceiver: Int,
    val falseReceiver: Int,
    var inspectionCount: Int = 0
)

class Day11 {
    companion object {
        private fun createOperation(operator: String, other: Int?): (Int) -> Int {
            return when (operator) {
                "+" -> { old: Int -> old + (other ?: old) }
                else -> { old: Int -> old * (other ?: old) }
            }
        }

        fun process(input: InputStream?): Int {
            if (input == null) {
                throw Exception("Input missing")
            }

            val numRegex = "\\d+".toRegex()
            val operatorRegex = "[+*]".toRegex()

            val monkeys = input.bufferedReader().useLines { lines ->
                lines.chunked(7)
                    .map { monkeyDesc ->
                        val items = numRegex.findAll(monkeyDesc[1]).map { it.value.toInt() }.toMutableList()

                        //println(monkeyDesc)

                        Monkey(
                            items,
                            createOperation(
                                operatorRegex.find(monkeyDesc[2])!!.value,
                                numRegex.find(monkeyDesc[2])?.value?.toInt()
                            ),
                            numRegex.find(monkeyDesc[3])!!.value.toInt(),
                            numRegex.find(monkeyDesc[4])!!.value.toInt(),
                            numRegex.find(monkeyDesc[5])!!.value.toInt(),
                        )
                    }.toList()
            }

            repeat(20) {
                for (monkey in monkeys) {
                    monkey.items.map {
                        monkey.operation(it) / 3
                    }.forEach {
                        monkey.inspectionCount++
                        if (it % monkey.test == 0) {
                            monkeys[monkey.trueReceiver].items.add(it)
                        } else {
                            monkeys[monkey.falseReceiver].items.add(it)
                        }
                    }
                    monkey.items.clear()
                }
            }

            return monkeys.sortedByDescending { it.inspectionCount }.take(2).map { it.inspectionCount }
                .fold(1) { acc, count -> acc * count }
        }

        fun process2(input: InputStream?): Int {
            TODO("Not yet implemented")
        }
    }

}
