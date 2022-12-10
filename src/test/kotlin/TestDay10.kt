import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay10 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day10.txt")?.openStream()
        val output = Day10.process(input)
        assertEquals(0, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day10.txt")?.openStream()
        val output = Day10.process2(input)
        assertEquals(0, output)
    }
}