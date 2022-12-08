import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay8 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day8.txt")?.openStream()
        val output = Day8.process(input)
        assertEquals(1560, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day8.txt")?.openStream()
        val output = Day8.process2(input)
        assertEquals(252000, output)
    }
}