package dag.graf

import dag.graf.function.XY
import dag.graf.function.XYFunction


class FunctionValues(private val XYFunction: XYFunction, private val fromT: Double, private val toT: Double, private val deltaT: Double) {
    var minX: Double = 0.0
    var maxX: Double = 0.0
    var minY: Double = 0.0
    var maxY: Double = 0.0
    private var currentT = fromT
    lateinit var xys: List<XY>
        private set

    fun calculate() {
        xys = mutableListOf<XY>().apply {
            while (currentT <= toT) {
                add(XYFunction.compute(currentT))
                currentT += deltaT
            }
        }.toList()

        if (xys.isNotEmpty()) {
            minX = xys.minBy { it.first }!!.first
            minY = xys.minBy { it.second }!!.second
            maxX = xys.maxBy { it.first }!!.first
            maxY = xys.maxBy { it.second }!!.second
        }
    }
}
