package dag.graf.function

import dag.graf.time
import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.log2
import kotlin.math.sin

class XYtest {

    @Test
    fun test() {
       val t =time {
            val f = { x: Double, y: Double -> log2( cos(y) - sin(x * x))/x }
            val deltaX = 0.001
            val deltaY = 0.001
            val epsilon = 0.005
            var x = -5.0
            var cnt = 0
           var eqZ = 0
            while (x <= 5.0) {
                var y = -25.0
                while (y <= 25.0) {
                    cnt++
                    val fxy = f(x, y)
                    if (abs(fxy) < epsilon) {
                        eqZ++
//                        println("fxy=$fxy x=$x y=$y")
                    }

                    y += deltaY
                }

                x += deltaX
            }

            println("#=${cnt}")
            println("z=${eqZ}")
        }

        println("T=${t}")
    }
}