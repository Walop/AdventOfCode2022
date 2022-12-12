import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay12 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day12.txt")?.openStream()
        val output = Day12.process(input)
        assertEquals(437, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day12.txt")?.openStream()
        val output = Day12.process2(input)
        assertEquals(
            0,
            output
        )
    }
}
