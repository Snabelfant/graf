package dag.graf.function

typealias XYT = Triple<Double, Double, Double>

abstract class XYTFunction() {
    fun compute(fromT: Double, toT: Double, deltaT: Double): List<XYT> {
        val xyts = mutableListOf<XYT>()
        var t = fromT

        while (t <= toT) {
            xyts += XYT(computeX(t), computeY(t), t)
            t += deltaT
        }

        return xyts.toList()
    }

    abstract fun computeX(t: Double): Double
    abstract fun computeY(t: Double): Double
}