package dag.graf

import dag.graf.function.FXYT
import dag.graf.function.XYTFunction

class XYTValues(private val XYTFunction: XYTFunction, fromT: Double, private val toT: Double, private val deltaT: Double) {
    var minX: Double = 0.0
    var maxX: Double = 0.0
    var minY: Double = 0.0
    var maxY: Double = 0.0
    private var currentT = fromT
    lateinit var xys: List<FXYT>
        private set

    fun calculate() {
        xys = mutableListOf<FXYT>().apply {
            while (currentT <= toT) {
                add(XYTFunction.compute(currentT))
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
