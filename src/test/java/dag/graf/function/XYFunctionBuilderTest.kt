package dag.graf.function

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XYFunctionBuilderTest {
    @Test
    @Throws(Exception::class)
    fun buildOK1() {
        val functionBuilder = XYFunctionBuilder("52")
        val function = functionBuilder.buildXYFunction()
        println(functionBuilder.diagnostics)
        val xys = function!!.compute(1.0, 5.0, 2.0, 4.0, 1.0, 1.0, 0.01)
        assertThat( xys).hasSize(0)
    }

    @Test
    @Throws(Exception::class)
    fun buildOK2() {
        val functionBuilder = XYFunctionBuilder("return x-y")
        val function = functionBuilder.buildXYFunction()
        println(functionBuilder.diagnostics)
        val xys = function!!.compute(1.0, 5.0, 2.0, 4.0, 1.0, 1.0, 0.01)
        assertThat( xys).hasSize(3)
        assertThat(xys[0].first).isEqualTo(2.0)
        assertThat(xys[0].second).isEqualTo(2.0)
        assertThat(xys[1].first).isEqualTo(3.0)
        assertThat(xys[1].second).isEqualTo(3.0)
        assertThat(xys[2].first).isEqualTo(4.0)
        assertThat(xys[2].second).isEqualTo(4.0)
    }

    @Test
    @Throws(Exception::class)
    fun buildOK3() {
        val functionBuilder = XYFunctionBuilder("0.001")
        val function = functionBuilder.buildXYFunction()!!
        println(functionBuilder.diagnostics)
        val xys = function.compute(1.0, 5.0, 2.0, 4.0, 1.0, 1.0, 0.0011)
        assertThat(xys).hasSize(15)
    }

    @Test
    @Throws(Exception::class)
    fun buildError() {
        val functionBuilder = XYFunctionBuilder("feil")
        val function = functionBuilder.buildXYFunction()
        assertThat(function).isNull()
        assertThat(functionBuilder.diagnostics).isNotNull()
    }

    @Test
    @Throws(Exception::class)
    fun test1() {
        val code = "sin(x)-cos(x)"
        val functionBuilder = XYFunctionBuilder(code)
        val function = functionBuilder.buildXYFunction()
        val xys = function!!.compute(-5.0, 5.0, -2.0, 4.0, 0.1,0.1,  0.001)
    }
}