import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay1 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day1.txt")?.openStream()
        val output = Day1.process(input)
        assertEquals(72718, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day1.txt")?.openStream()
        val output = Day1.process2(input)
        assertEquals(213089, output)
    }
}