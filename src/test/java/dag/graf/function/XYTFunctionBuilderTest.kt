package dag.graf.function

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XYTFunctionBuilderTest {
    @Test
    @Throws(Exception::class)
    fun buildOK() {
        val functionBuilder = XYTFunctionBuilder("return 52", "return 67")
        val function = functionBuilder.buildXYTFunction()!!
        println(functionBuilder.diagnostics)
        val xyts = function.compute(1.0, 5.0, 1.0)
        assertThat(xyts).size().isEqualTo(5)
        assertThat(xyts.all { it.first == 52.0 }).isTrue()
        assertThat(xyts.all { it.second == 67.0 }).isTrue()
    }

    @Test
    @Throws(Exception::class)
    fun buildError() {
        val functionBuilder = XYTFunctionBuilder("feil", "return 67")
        val function = functionBuilder.buildXYTFunction()
        assertThat(function).isNull()
        assertThat(functionBuilder.diagnostics).isNotNull()
    }

    @Test
    @Throws(Exception::class)
    fun test1() {
        val xCode = "t"
        val yCode = "500/t"
        val functionBuilder = XYTFunctionBuilder(xCode, yCode)
        val function = functionBuilder.buildXYTFunction()!!
        val xyts = function.compute(1.0, 501.0, 1.0)
        assertThat(xyts).size().isEqualTo(501)
        assertThat(xyts[500].first).isEqualTo(501.0)
        assertThat(xyts[500].second).isEqualTo(500.0 / 501.0)
        assertThat(xyts[500].third).isEqualTo(501.0)
    }

    @Test
    @Throws(Exception::class)
    fun test2() {
        val xCode = "t * cos(t)"
        val yCode = "t * sin(t)"
        val functionBuilder = XYTFunctionBuilder(xCode, yCode)
        val function = functionBuilder.buildXYTFunction()!!
        assertThat(functionBuilder.diagnostics).isNull()
        val xyts = function.compute(0.0, 9999999.0, 1.0)
        assertThat(xyts).size().isEqualTo(10_000_000)
        assertThat(xyts[1].first).isEqualTo(1 * kotlin.math.cos(1.0))
        assertThat(xyts[1].second).isEqualTo(1 * kotlin.math.sin(1.0))
        assertThat(xyts[1].third).isEqualTo(1.0)
    }
}