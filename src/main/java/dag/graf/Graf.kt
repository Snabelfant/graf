package dag.graf

import javafx.animation.RotateTransition
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.*
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.transform.Scale
import javafx.stage.Stage
import javafx.util.Duration


class Graf : Application() {
    override fun start(primaryStage: Stage) {
        val line = Line()
        line.startX = 100.0;
        line.startY = 150.0;
        line.endX = 500.0;
        line.endY = 180.0;
        val root = Group(line)
        val scene = Scene(root, 600.toDouble(), 300.toDouble())
        scene.fill = Color.BROWN;
        primaryStage.title = "Graf"
        primaryStage.scene = scene


        primaryStage.show()
        val text = Text()
        text.font = Font(45.0)
        text.x = 50.0
        text.y = 150.0
        text.text = "Welcome to Tutorialspoint"
        root.children += text

        val path = Path()
        val moveTo = MoveTo(108.0, 71.0)
        val line1 = LineTo(321.0, 161.0)
        val line2 = LineTo(126.0, 232.0)
        val line3 = LineTo(232.0, 52.0)
        val line4 = LineTo(269.0, 250.0)
        val line5 = ArcTo(10.0,15.0, 20.0, 108.0, 71.0, true, true)
        path.elements.add(moveTo)
        path.elements.addAll(line1, line2, line3, line4, line5)
        root.children += path

        val scale = Scale()
        scale.x = 0.5
        scale.y = 1.5
//        scale.pivotX = 300.0
//        scale.pivotY = 135.0
        path.transforms.addAll(scale)

        val hexagon = Polygon()
        hexagon.points.addAll(arrayOf(
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
                150.0, 150.0))
        hexagon.fill = Color.BLUE
        val rotateTransition = RotateTransition()
        rotateTransition.duration = Duration.millis(1000.0)
        rotateTransition.node = hexagon
        rotateTransition.byAngle = 360.0
        rotateTransition.cycleCount = 50
        rotateTransition.isAutoReverse = false
        rotateTransition.play()

        root.children += hexagon

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Graf::class.java)
        }
    }
}