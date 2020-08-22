package dag.graf.function

typealias XY = Pair<Double, Double>

abstract class XYFunction() {
    fun compute(fromX : Double, toX : Double,
                fromY : Double,toY : Double,
                deltaX : Double,deltaY:Double,
                epsilon :Double): List<XY> {
        val xys = mutableListOf<XY>()
        var x = fromX

        while (x <= toX) {
            var y = fromY

            while (y <= toY) {
                val v = compute(x, y)
                if (v < epsilon) {
                    xys += Pair(x, y)
                }

                y += deltaY
            }

            x += deltaX
        }

        return xys.toList()
    }

    protected abstract fun compute(x: Double, y : Double): Double
}