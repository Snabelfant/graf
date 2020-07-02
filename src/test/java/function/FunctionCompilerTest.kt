package function;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class FunctionCompilerTest {

    private FunctionCompiler compiler;

    @BeforeEach
    void init() throws IOException {
        compiler = new FunctionCompiler();
    }

    @Test
    void compileOK() throws Exception {
        final boolean success = compiler.compile("test.Z", "package test; class Z{}");
       assertSame("",compiler.getDiagnostics() );
        assertTrue(success);
    }

    @Test
    void compileFail() throws Exception {
        final boolean success = compiler.compile("test.Z", "package test; class Z{ int z = t;}");
        assertFalse(success);
        assertTrue(compiler.getDiagnostics().contains("cannot find symbol"));
    }

}