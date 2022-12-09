import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay9 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day9.txt")?.openStream()
        val output = Day9.process(input)
        assertEquals(6090, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day9.txt")?.openStream()
        val output = Day9.process2(input)
        assertEquals(2566, output)
    }
}