import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val g = Grid(input.lastIndex, input[0].lastIndex)

        val startPoints = input
            .findSymbolsOccurrences('X')

        return startPoints.map { sp ->
            directions.map { sp to it }
        }.flatten()
            .filter { startWithDirection ->
                val start = startWithDirection.first
                val direction = startWithDirection.second
                val potentialWordEnd = start.addTimes(direction, 3)

                if (g.pointWithin(potentialWordEnd)) {
                    false
                } else {
                    var t = true
                    for (i in lookupFor.indices) {
                        if (input.getLetterByPoint(start.addTimes(direction, i + 1)) != lookupFor[i]) {
                            t = false
                            break
                        }
                    }
                    t
                }
            }.size
    }

    fun part2(input: List<String>): Int {
        val g = Grid(input.lastIndex, input[0].lastIndex)
        val startPoints = input.findSymbolsOccurrences('A')

        return startPoints
            .filter { sp ->
                lookupForWithDirections.any { ld ->
                    ld.all { p ->
                        val lookupIn = sp.add(p.second)
                        g.pointWithin(lookupIn) && input.getLetterByPoint(lookupIn) == p.first
                    }
                }
            }.size
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

fun List<String>.getLetterByPoint(p: Point): Char {
    return this[p.a][p.b]
}

val lookupFor = listOf('M', 'A', 'S')
val lookupForWithDirections = listOf(
    listOf(
        'M' to Point(-1, -1), 'M' to Point(-1, 1),
        'S' to Point(1, -1), 'S' to Point(1, 1)
    ),
    listOf(
        'M' to Point(-1, 1), 'M' to Point(1, 1),
        'S' to Point(1, -1), 'S' to Point(-1, -1)
    ),
    listOf(
        'M' to Point(1, -1), 'M' to Point(1, 1),
        'S' to Point(-1, -1), 'S' to Point(-1, 1)
    ),
    listOf(
        'M' to Point(1, -1), 'M' to Point(-1, -1),
        'S' to Point(-1, 1), 'S' to Point(1, 1)
    )
)
val directions = listOf(
    Point(0, 1),
    Point(1, 0),
    Point(0, -1),
    Point(-1, 0),
    Point(-1, -1),
    Point(-1, 1),
    Point(1, 1),
    Point(1, -1),
)