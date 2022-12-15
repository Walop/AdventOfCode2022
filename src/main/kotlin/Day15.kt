import java.io.InputStream
import kotlin.math.absoluteValue

data class Sensor(val pos: Vec2, val range: Int)

class Day15 {
    companion object {
        private fun readSensors(input: InputStream?): Pair<List<Sensor>, Set<Vec2>> {
            if (input == null) {
                throw Exception("Input missing")
            }

            val numRegex = "[\\d\\-]+".toRegex()

            val coords = input.bufferedReader().useLines { lines ->
                lines.flatMap {
                    numRegex.findAll(it).map { res -> res.value.toInt() }
                }.toList()
            }

            val filled = coords.chunked(2).map { Vec2(it[0], it[1]) }.toSet()
            val sensors = coords.chunked(4)
                .map { Sensor(Vec2(it[0], it[1]), (it[0] - it[2]).absoluteValue + (it[1] - it[3]).absoluteValue) }

            return Pair(sensors, filled)
        }

        fun process(input: InputStream?, rowNum: Int): Long {
            val (sensors, filled) = readSensors(input)

            //sensors.forEach { println(it) }
            //filled.forEach { println(it) }

            val leftSensor = sensors.minBy { it.pos.x - it.range }
            val rightSensor = sensors.maxBy { it.pos.x + it.range }

            val minX = leftSensor.pos.x - leftSensor.range
            val maxX = rightSensor.pos.x + rightSensor.range

            println("$minX-$maxX: ${(minX - maxX).absoluteValue}")

            // val map = mutableListOf<Char>()

            val sum = (minX..maxX)
                .toList()
                .parallelStream()
                .map { x ->
                    val inRange = sensors.any { sensor ->
                        (sensor.pos.x - x).absoluteValue + (sensor.pos.y - rowNum).absoluteValue <= sensor.range
                    }
                    if (!inRange) {
                        // map.add('.')
                        0
                    } else {
                        if (filled.contains(Vec2(x, rowNum))) {
                            // map.add('.')
                            0
                        } else {
                            // map.add('#')
                            1
                        }
                    }
                }.filter { it != 0 }.count()

            // map.add('.')

            //println(map.joinToString("").trim('.').count { it == '.' })

            //File("C:\\Temp\\map_15.txt").writeText(map.joinToString(""))

            return sum
        }

        fun process2(input: InputStream?): Long {
            val (sensors, filled) = readSensors(input)

            var maxX = sensors.maxOf { it.pos.x }

            var current = Vec2(0, 0)

            var covering: Sensor? = null

            while (true) {
                covering = sensors.firstOrNull { sensor ->
                    sensor != covering &&
                            (sensor.pos.x - current.x).absoluteValue + (sensor.pos.y - current.y).absoluteValue <= sensor.range
                }

                if (covering == null) {
                    break
                }

                val newPos = ((covering.pos.x + covering.range) - (covering.pos.y - current.y).absoluteValue) + 1

                if (newPos < maxX) {
                    current.x = newPos
                } else {
                    current.x = 0
                    current.y++
                }
            }

            // println(current)

            return 4_000_000L * current.x.toLong() + current.y.toLong()
        }
    }

}
