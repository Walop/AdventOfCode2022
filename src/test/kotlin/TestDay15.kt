import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay15 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day15.txt")?.openStream()
        val output = Day15.process(input, 2000000)
        assertEquals(5525990, output)
        // too low 3341054, 5336763
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day15.txt")?.openStream()
        val output = Day15.process2(input)
        assertEquals(
            0,
            output
        )
    }
}
