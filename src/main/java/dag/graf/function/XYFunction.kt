package dag.graf.function

typealias XY = Triple<Double, Double, Double>

abstract class XYFunction() {
    var lastX: Double? = null
        private set
    var lastY: Double? = null
        private set
    var lastT: Double? = null
        private set

    fun compute(t: Double) = XY(computeX(t).also { lastX = it }, computeY(t).also { lastY = it }, t.also { lastT = it })

    abstract fun computeX(t: Double): Double
    abstract fun computeY(t: Double): Double
}