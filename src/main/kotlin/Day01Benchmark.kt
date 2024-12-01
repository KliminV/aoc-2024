package main

import kotlinx.benchmark.*
import part2Input
import part2Matcher
import part2Slow
import readInput

@State(Scope.Benchmark)
class Day01Benchmark {
    private val input = readInput("Day01")
    private var list = emptyList<Int>()
    private var map = emptyMap<Int, Int>()

    @Setup
    fun setUp() {
        val v = part2Input(input)
        list = v.first
        map = v.second
    }

    @Benchmark
    fun part2Bench(): Int {
        return part2Matcher(list, map)
    }

    @Benchmark
    fun part2Slo(): Int {
        return part2Slow(list, map)
    }
}