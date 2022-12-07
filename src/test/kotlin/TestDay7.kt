import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay7 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day7.txt")?.openStream()
        val output = Day7.process(input)
        assertEquals(1555642, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day7.txt")?.openStream()
        val output = Day7.process2(input)
        assertEquals(5974547, output)
    }
}