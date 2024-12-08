import kotlin.math.abs

fun main() {
    fun foundAndValid(f: Int, l: Int) = (f == -1 || l == -1 || l > f)

    fun allRulesValid(
        rules: List<Pair<Int, Int>>,
        u: List<Int>
    ) = rules.all { r ->
        val f = u.indexOf(r.first)
        val l = u.indexOf(r.second)
        foundAndValid(f, l)
    }

    fun rulesUpdates(input: List<String>): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val inputs = input.joinToString("\n").split("\n\n")
        val rules = inputs[0].split("\n").map {
            it.split("|")
                .map { i -> i.toInt() }
        }
            .map { it[0] to it[1] }
        val updates = inputs[1].split("\n").map { l ->
            l.split(",").map { it.toInt() }.toList()
        }
        return rules to updates
    }


    fun anyRoleVoilated(
        rs: List<Pair<Int, Int>>,
        res: MutableList<Int>
    ) = rs.any { r ->
        val f = res.indexOf(r.first)
        val l = res.indexOf(r.second)
        !foundAndValid(f, l)
    }

    fun mixUntilGood(u: List<Int>, rs: List<Pair<Int, Int>>): List<Int> {
        val res = u.toMutableList()
        while (anyRoleVoilated(rs, res)) {
            val fi = rs.first { r ->
                val f = res.indexOf(r.first)
                val l = res.indexOf(r.second)
                !foundAndValid(f, l)
            }
            val fix = res.indexOf(fi.first)
            val six = res.indexOf(fi.second)
            val swap = res[fix]
            res[fix] = res[six]
            res[six] = swap
        }
        return res
    }

    fun part1(input: List<String>): Int {
        val (rules, updates) = rulesUpdates(input)
        return updates
            .filter { u ->
                allRulesValid(rules, u)
            }
            .sumOf { u ->
                u[u.size / 2]
            }
    }

    fun part2(input: List<String>): Int {
        val (rules, updates) = rulesUpdates(input)
        return updates
            .filterNot { u ->
                allRulesValid(rules, u)
            }
            .map { u -> mixUntilGood(u, rules) }
            .sumOf { u -> u[u.size / 2] }
    }

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    timeTook({ part1(input).println() }, "part1")
    timeTook({ part2(input).println() }, "part2")
}



