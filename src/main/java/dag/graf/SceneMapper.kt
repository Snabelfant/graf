package dag.graf

typealias SXY = Pair<Double,Double>

abstract class SceneMapper (protected val functionValues : FunctionValues) {

    abstract fun toSXY() : List<SXY>
//    abstract fun toFXY(sX : Int, sY : Int)

}

class FilledSceneMapper(functionValues: FunctionValues, private val sceneWidth: Double, private val sceneHeight: Double) : SceneMapper(functionValues) {
    override fun toSXY(): List<SXY> {
        val minFX = functionValues.minX
        val maxFX = functionValues.maxX
        val fXperSX = (maxFX - minFX)/sceneWidth
        val minFY = functionValues.minY
        val maxFY = functionValues.maxY
        val fYperSY = (maxFY - minFY)/sceneHeight

        println("$minFX $maxFX $minFY $maxFY ")
        return functionValues.xys.map{ SXY(((it.first-minFX)*fXperSX), ((it.second-minFY)*fYperSY) ) }
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

