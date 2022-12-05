import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay5 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day5.txt")?.openStream()
        val output = Day5.process(input)
        assertEquals("VRWBSFZWM", output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day5.txt")?.openStream()
        val output = Day5.process2(input)
        assertEquals("", output)
    }
}