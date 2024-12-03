import kotlin.math.abs

fun main() {

    fun findAndProcessPairs(line: String): Int {
        val match = rex.findAll(line).toList()
        var sum = 0
        for (i in match.indices) {
            val gr = match[i].value
            val matchOperation = operators.find(gr)
            val (a, b) = matchOperation!!.destructured
            sum += a.toInt() * b.toInt()
        }
        return sum
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            findAndProcessPairs(line)
        }
    }

    fun part2(input: List<String>): Int {
        val instructions = input.joinToString().split("do()").toList()
            .map { line -> line.substringBefore("don't()") }
        return instructions.sumOf { line ->
            findAndProcessPairs(line)
        }
    }
    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

val rex = "mul\\(\\d+,\\d+\\)".toRegex()
val operators = "(\\d+),(\\d+)".toRegex()
