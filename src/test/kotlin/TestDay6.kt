import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay6 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day6.txt")?.openStream()
        val output = Day6.process(input)
        assertEquals(1760, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day6.txt")?.openStream()
        val output = Day6.process2(input)
        assertEquals(2974, output)
    }
}