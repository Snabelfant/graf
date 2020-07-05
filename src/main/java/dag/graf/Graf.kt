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

        val stageSizeListener = ChangeListener<Number> { _, _, _ -> println("Height: ${primaryStage.height} Width: ${primaryStage.width}"); }
        scene.widthProperty().addListener(stageSizeListener)
        scene.heightProperty().addListener(stageSizeListener)

        root.children += graf()
        primaryStage.show()
    }

    private fun graf(): Path {
        val function = FunctionBuilder("10*t+ r.nextInt(5)", "10*t*abs(sin(t)+cos(t))").build()
        val calculator = FunctionValues(function!!, 1.0, 200.0, 0.1)
        calculator.calculate()
        val xys = calculator.xys

        return Path().apply {
            elements += MoveTo(xys.first().first, xys.first().second)
            elements += xys.drop(1).map { LineTo(it.first, it.second) }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Graf::class.java)
        }
    }
}