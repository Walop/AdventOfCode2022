import org.junit.jupiter.api.Assertions.assertEquals

class TestDay1 {
    @org.junit.jupiter.api.Test
    internal fun part1() {
        val input = this::class.java.classLoader.getResource("input_day1.txt")?.openStream()
        val output = Day1.process(input)
        assertEquals(72718, output)
    }
}