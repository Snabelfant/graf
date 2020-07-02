package function

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class FunctionCompilerTest {
    private var compiler = FunctionCompiler()

    @Test
    @Throws(Exception::class)
    fun compileOK() {
        val success = compiler.compile("package test; class Z{}")
        Assertions.assertEquals("", compiler.diagnostics)
        Assertions.assertTrue(success)
    }

    @Test
    @Throws(Exception::class)
    fun compileFail() {
        val success = compiler.compile("package test; class Z{ int z = t;}")
        Assertions.assertFalse(success)
        Assertions.assertTrue(compiler.diagnostics.contains("cannot find symbol"))
    }
}