package function

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class FunctionBuilderTest {
    @Test
    @Throws(Exception::class)
    fun buildOK() {
        val functionBuilder = FunctionBuilder( "return 52", "return 67")
        val function = functionBuilder.build()
        println(functionBuilder.diagnostics)
       assertEquals(52.0, function!!.computeX(1.0))
        Assertions.assertEquals(67.0, function!!.computeY(1.0))
        val xy = function.compute(11.0)
        Assertions.assertEquals(52.0, xy.x)
        Assertions.assertEquals(67.0,xy.y)
        assertEquals(11.0, function.lastT)
        assertEquals(52.0, function.lastX)
        assertEquals(67.0, function.lastY)
    }

    @Test
    @Throws(Exception::class)
    fun buildError() {
        val functionBuilder = FunctionBuilder("feil", "return 67")
        val function = functionBuilder.build()
        assertNull(function)
        assertNotNull(functionBuilder.diagnostics)
    }

    @Test
    @Throws(Exception::class)
    fun test1() {
        val xCode = "return t"
        val yCode = "return 500/t"
        val functionBuilder = FunctionBuilder(xCode, yCode)
        val function = functionBuilder.build()
        for (t in 1..501) {
            val xy = function!!.compute(t.toDouble())
            println(xy.x.toString() + " " + xy.y)
            if (xy.y < 1) {
                return
            }
        }
        fail<Any>()
    }

    @Test
    @Throws(Exception::class)
    fun test2() {
        val xCode = "return t * cos(t)"
        val yCode = "return t * sin(t)"
        val functionBuilder = FunctionBuilder( xCode, yCode)
        val function = functionBuilder.build()
        assertNotNull(function, functionBuilder.diagnostics)
        for (t in 0..9999999) {
            function!!.compute(t.toDouble())
        }
    }
}