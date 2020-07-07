package dag.graf

import dag.graf.function.FunctionBuilder
import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.shape.LineTo
import javafx.scene.shape.MoveTo
import javafx.scene.shape.Path
import javafx.stage.Stage


class Graf : Application() {
    override fun start(primaryStage: Stage) {
        val root = Group()
        val scene = Scene(root, 600.toDouble(), 300.toDouble())
        primaryStage.title = "Graf"
        primaryStage.scene = scene

        val sceneSizeListener = ChangeListener<Number> { _, _, _ -> println("Height: ${scene.height} Width: ${scene.width}"); }
        scene.widthProperty().addListener(sceneSizeListener)
        scene.heightProperty().addListener(sceneSizeListener)

        root.children += graf(scene)
        primaryStage.show()
    }

    private fun graf(scene: Scene): Path {
        val function = FunctionBuilder("10*t+ r.nextInt(5)", "10*t*abs(sin(t)+cos(t))").build()
        val functionValues = FunctionValues(function!!, 1.0, 10.0, 1.0)
        functionValues.calculate()

        val sceneMapper = FilledSceneMapper(functionValues, scene.width, scene.height )
        val sXYs = sceneMapper.toSXY()

        return Path().apply {
            elements += MoveTo(sXYs.first().first, sXYs.first().second)
            elements += sXYs.drop(1).map { LineTo(it.first, it.second) }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Graf::class.java)
        }
    }
}