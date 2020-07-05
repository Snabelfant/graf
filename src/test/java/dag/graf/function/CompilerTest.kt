package dag.graf.function

import dag.graf.compiler.Compiler
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class CompilerTest {
    private var compiler = Compiler()

    @Test
    @Throws(Exception::class)
    fun compileOK() {
        val success = compiler.compile("package test; class Ztest{}", "Ztest")
        Assertions.assertEquals("", compiler.diagnostics)
        Assertions.assertTrue(success)
    }

    @Test
    @Throws(Exception::class)
    fun compileFail() {
        val success = compiler.compile("package test; class Ztest{ int z = t;}", "Ztest")
        Assertions.assertFalse(success)
        Assertions.assertTrue(compiler.diagnostics.contains("cannot find symbol"))
    }
}