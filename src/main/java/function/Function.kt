package function

abstract class Function {
    var lastX: Double? = null
        private set
    var lastY: Double? = null
        private set
    var lastT: Double? = null
        private set

    fun compute(t: Double) : XY {
        lastT = t
        return XY(computeX(t).also { lastX = it }, computeY(t).also { lastY = it })
    }

    abstract fun computeX(t: Double): Double
    abstract fun computeY(t: Double): Double
}