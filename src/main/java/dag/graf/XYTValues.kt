package dag.graf

import dag.graf.function.XYT
import dag.graf.function.XYTFunction

class XYTValues(private val XYTFunction: XYTFunction, private val fromT: Double, private val toT: Double, private val deltaT: Double) {
    var minX: Double = 0.0
    var maxX: Double = 0.0
    var minY: Double = 0.0
    var maxY: Double = 0.0
    lateinit var xys: List<XYT>
        private set

    fun calculate() {
        xys = XYTFunction.compute(fromT, toT, deltaT)

        if (xys.isNotEmpty()) {
            minX = xys.minBy { it.first }!!.first
            minY = xys.minBy { it.second }!!.second
            maxX = xys.maxBy { it.first }!!.first
            maxY = xys.maxBy { it.second }!!.second
        }
    }
}
