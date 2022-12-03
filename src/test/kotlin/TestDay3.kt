import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay3 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day3.txt")?.openStream()
        val output = Day3.process(input)
        assertEquals(8185, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day3.txt")?.openStream()
        val output = Day3.process2(input)
        assertEquals(0, output)
    }
}