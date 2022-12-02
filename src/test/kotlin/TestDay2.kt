import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay2 {
    @Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day2.txt")?.openStream()
        val output = Day2.process(input)
        assertEquals(11063, output)
    }

    @Test
    internal fun part2() {
        val input = this::class.java.classLoader.getResource("input_day2.txt")?.openStream()
        val output = Day2.process2(input)
        assertEquals(10349, output)
    }
}