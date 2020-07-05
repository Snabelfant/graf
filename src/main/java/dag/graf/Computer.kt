package dag.graf

import dag.graf.function.XY
import dag.graf.function.XYFunction


class Computer(private val XYFunction: XYFunction, private val fromT: Double, private val toT: Double, private val deltaT: Double) {
    private var currentT = fromT

    fun hasNext() = currentT <= toT
    fun next() =  XYFunction.compute(currentT).also { currentT += deltaT }
    fun next(n : Int) = mutableListOf<XY>().apply { while (hasNext()) { add(next())} }.take(n).toList()
    fun all() = mutableListOf<XY>().apply { while (hasNext()) { add(next())} }.toList()
}
