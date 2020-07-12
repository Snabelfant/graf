package dag.graf

typealias SXY = Pair<Double,Double>

abstract class SceneMapper (protected val XYTValues : XYTValues) {

    abstract fun toSXY() : List<SXY>
//    abstract fun toFXY(sX : Int, sY : Int)

}

class FilledSceneMapper(XYTValues: XYTValues, private val sceneWidth: Double, private val sceneHeight: Double) : SceneMapper(XYTValues) {
    override fun toSXY(): List<SXY> {
        val minFX = XYTValues.minX
        val maxFX = XYTValues.maxX
        val fXperSX = (maxFX - minFX)/sceneWidth
        val minFY = XYTValues.minY
        val maxFY = XYTValues.maxY
        val fYperSY = (maxFY - minFY)/sceneHeight
val fPerS = Math.max(fXperSX, fYperSY)
        println("$minFX $maxFX $fXperSX $minFY $maxFY $fYperSY")
        println(XYTValues.xys.size)
        return XYTValues.xys.map{ SXY(((it.first-minFX)/fPerS), ((it.second-minFY)/fPerS) ) }.also { println(it.size) }
    }

//    override fun toFXY(sX: Int, sY: Int) {
//        return FXY()
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
}

//class ZoomedSceneMapper(functionValues: FunctionValues, private val sceneWidth : Int, private val sceneHeight : Int, ) : SceneMapper(functionValues) {
//    override fun toSXY(): List<SXY> {
//        val minFX = functionValues.minX
//        val maxFX = functionValues.maxX
//        val fXperSX = (maxFX - minFX)/sceneWidth
//        val minFY = functionValues.minY
//        val maxFY = functionValues.maxY
//        val fYperSY = (maxFY - minFY)/sceneHeight
//
//        return functionValues.xys.map{ SXY(((it.first-minFX)*fXperSX).toInt(), ((it.second-minFY)*fYperSY).toInt() ) }
//    }
//}

