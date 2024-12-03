import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val parsedData = input
            .map { it.split(" ").mapNotNull { it.toIntOrNull() }.toList() }
            .map { list -> list.windowed(2, 1, false).map { it[1] - it[0] } }
        return parsedData.calculateScore(false) + parsedData.calculateScore(true)
    }

    fun part2(input: List<String>): Int {
        val parsedData = input
            .map { it.split(" ").mapNotNull { it.toIntOrNull() }.toList() }
        return parsedData.count { numbers ->
            numbers.indices.any {
                val skipped = numbers.toMutableList().apply { removeAt(it) }
                isLineSafeFunc(skipped)
            }
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun List<List<Int>>.calculateScore(reversed: Boolean): Int {
    fun condition(step: Int): Boolean = if (reversed) step > -1 || step < -3 else step > 3 || step < 1
    return this.sumOf { list ->
        val score = if (list.any { condition(it) }) 0 else 1
        score
    }
}

fun isLineSafeFunc(numbers: List<Int>): Boolean {
    val differences = numbers.zipWithNext { a, b -> a - b }
    return differences.all { it in -3..3 } &&
            (differences.all { it > 0 } || differences.all { it < 0 })
}

