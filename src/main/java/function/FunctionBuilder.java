package function;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class FunctionBuilder {
    private String xCode;
    private String yCode;
    private FunctionCompiler compiler;
    private String diagnostics;

    public FunctionBuilder(String xCode, String yCode, FunctionCompiler compiler) {
        this.xCode = xCode;
        this.yCode = yCode;
        this.compiler = compiler;
    }

    public Function build() throws Exception {
        final boolean success = compiler.compile("function.XFunction", getCompleteSource());

        if (success) {
            final Class<?> cls = Class.forName("function.XFunction");
            return (Function) cls.getConstructor().newInstance();
        } else {
            diagnostics = compiler.getDiagnostics();
            return null;
        }
    }

    public String getDiagnostics() {
        return diagnostics;
    }
    private String getCompleteSource() {
        return new StringBuilder()
                .append("package function;")
                .append("public class XFunction extends Function{")
                .append("@Override ")
                .append("double computeX(double t){")
                .append(xCode)
                .append(";}")
                .append("@Override ")
                .append("double computeY(double t){")
                .append(yCode)
                .append(";}")
                .append("}")
                .toString();
    }
}
