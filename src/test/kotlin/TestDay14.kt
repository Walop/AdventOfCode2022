import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay14 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day14.txt")?.openStream()
        val output = Day14.process(input)
        assertEquals(1133, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day14.txt")?.openStream()
        val output = Day14.process2(input)
        assertEquals(
            0,
            output
        )
    }
}
