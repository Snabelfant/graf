package dag.graf.function

import dag.graf.function.FunctionBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class XYTFunctionBuilderTest {
    @Test
    @Throws(Exception::class)
    fun buildOK() {
        val functionBuilder = FunctionBuilder("return 52", "return 67")
        val function = functionBuilder.buildXYTFunction()
        println(functionBuilder.diagnostics)
        assertEquals(52.0, function!!.computeX(1.0))
        Assertions.assertEquals(67.0, function.computeY(1.0))
        val (x, y, t) = function.compute(11.0)
        Assertions.assertEquals(52.0, x)
        Assertions.assertEquals(67.0, y)
        assertEquals(11.0, function.lastT)
        assertEquals(52.0, function.lastX)
        assertEquals(67.0, function.lastY)
    }

    @Test
    @Throws(Exception::class)
    fun buildError() {
        val functionBuilder = FunctionBuilder("feil", "return 67")
        val function = functionBuilder.buildXYTFunction()
        assertNull(function)
        assertNotNull(functionBuilder.diagnostics)
    }

    @Test
    @Throws(Exception::class)
    fun test1() {
        val xCode = "t"
        val yCode = "500/t"
        val functionBuilder = FunctionBuilder(xCode, yCode)
        val function = functionBuilder.buildXYTFunction()
        for (t in 1..501) {
            val (x, y, t) = function!!.compute(t.toDouble())
            if (y < 1) {
                return
            }
        }
        fail<Any>()
    }

    @Test
    @Throws(Exception::class)
    fun test2() {
        val xCode = "t * cos(t)"
        val yCode = "t * sin(t)"
        val functionBuilder = FunctionBuilder(xCode, yCode)
        val function = functionBuilder.buildXYTFunction()
        assertNotNull(function, functionBuilder.diagnostics)
        for (t in 0..9999999) {
            function!!.compute(t.toDouble())
        }
    }
}