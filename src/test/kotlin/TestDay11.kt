import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay11 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day11.txt")?.openStream()
        val output = Day11.process(input)
        assertEquals(66802L, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day11.txt")?.openStream()
        val output = Day11.process2(input)
        assertEquals(
            21800916620L,
            output
        )
    }
}
