import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val parsed = input
            .map { parseLine(it) }
            .unzip()
        val arr1 = parsed.first.sorted()
        val arr2 = parsed.second.sorted()
        var result = 0
        for (i in arr1.indices) {
            result += abs(arr1[i] - arr2[i])
        }
        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun parseLine(s: String): Pair<Int, Int> {
    val split = s.split(" ")
    val numbers = split.mapNotNull { it.toIntOrNull() }
    require(numbers.size == 2) {
        println("Line should contain 2 numbers")
    }
    return Pair(numbers[0], numbers[1])
}

fun part2(input: List<String>): Int {
    val (arr1, arr2) = part2Input(input)
    return part2Matcher(arr1, arr2)
}

fun part2Input(input: List<String>): Pair<List<Int>, Map<Int, Int>> {
    val arr1 = mutableListOf<Int>()
    val arr2 = mutableMapOf<Int, Int>()
    input
        .map { parseLine(it) }
        .onEach {
            arr1.add(it.first)
            arr2[it.second] = arr2.getOrDefault(it.second, 0) + 1
        }
    return Pair(arr1, arr2)
}

fun part2Matcher(list: List<Int>, map: Map<Int, Int>): Int {
    return list.sumOf { it * map.getOrDefault(it, 0) }
}

fun part2Slow(list: List<Int>, map: Map<Int, Int>): Int {
    return list.fold(mutableMapOf<Int, Int>()) { acc, num ->
        acc[num] = acc.getOrDefault(num, 0) + 1
        acc
    }
        .entries
        .sumOf { map.getOrDefault(it.key, 0) * it.key * it.value }
}