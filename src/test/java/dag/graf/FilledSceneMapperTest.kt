package dag.graf

import dag.graf.function.FunctionBuilder
import org.junit.jupiter.api.Test

internal class FilledSceneMapperTest {

    @Test
    fun test() {
        val xyFunction = FunctionBuilder("t", "t").build()
        val functionValues = FunctionValues(xyFunction!!, 0.0, 3.0, 1.0)
        functionValues.calculate()
        println(functionValues.xys)
        val filledSceneMapper = FilledSceneMapper(functionValues, 3.0, 3.0)

        val sceneXYs = filledSceneMapper.toSXY()

        println(sceneXYs)
    }
}