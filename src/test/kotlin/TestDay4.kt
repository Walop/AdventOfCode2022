import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay4 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day4.txt")?.openStream()
        val output = Day4.process(input)
        assertEquals(580, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day4.txt")?.openStream()
        val output = Day4.process2(input)
        assertEquals(895, output)
    }
}