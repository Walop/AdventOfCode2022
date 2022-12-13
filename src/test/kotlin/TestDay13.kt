import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay13 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day13.txt")?.openStream()
        val output = Day13.process(input)
        assertEquals(5588, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day13.txt")?.openStream()
        val output = Day13.process2(input)
        assertEquals(
            0,
            output
        )
    }
}
