package dag.graf.function

internal class XYTFunctionBuilderTest {
    @Test
    @Throws(Exception::class)
    fun buildOK() {
        val functionBuilder = XYTFunctionBuilder("return 52", "return 67")
        val function = functionBuilder.buildXYTFunction()
        println(functionBuilder.diagnostics)
        assertEquals(52.0, function!!.computeX(1.0))
        Assertions.assertEquals(67.0, function.computeY(1.0))
        val (x, y, t) = function.compute(11.0)
        Assertions.assertEquals(52.0, x)
        Assertions.assertEquals(67.0, y)
    }

    @Test
    @Throws(Exception::class)
    fun buildError() {
        val functionBuilder = XYTFunctionBuilder("feil", "return 67")
        val function = functionBuilder.buildXYTFunction()
        assertNull(function)
        assertNotNull(functionBuilder.diagnostics)
    }

    @Test
    @Throws(Exception::class)
    fun test1() {
        val xCode = "t"
        val yCode = "500/t"
        val functionBuilder = XYTFunctionBuilder(xCode, yCode)
        val function = functionBuilder.buildXYTFunction()
        val xyts = function!!.compute(1.0, 501.0, 1.0)
        assertThat()
    }

    @Test
    @Throws(Exception::class)
    fun test2() {
        val xCode = "t * cos(t)"
        val yCode = "t * sin(t)"
        val functionBuilder = XYTFunctionBuilder(xCode, yCode)
        val function = functionBuilder.buildXYTFunction()
        assertNotNull(function, functionBuilder.diagnostics)
        for (t in 0..9999999) {
            function!!.compute(t.toDouble())
        }
    }
}