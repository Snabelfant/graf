package function;

import test.AdvancedCompilationTest3;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class FunctionCompiler {
    private JavaCompiler compiler;
    private DiagnosticCollector<? super JavaFileObject> diagnosticsCollector;
    private StandardJavaFileManager fileManager;

    public FunctionCompiler() throws IOException {
        compiler = ToolProvider.getSystemJavaCompiler();
        diagnosticsCollector = new DiagnosticCollector();
        fileManager = compiler.getStandardFileManager(diagnosticsCollector, null, null);
        File outputDir = new File("C:\\Users\\dmvv\\IdeaProjects\\graf\\target\\classes");
        outputDir.mkdirs();
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(outputDir));
    }

    boolean compile(String className, String contents) throws Exception {
        JavaFileObject javaFileObject = new JavaObjectFromString(className, contents);
        Iterable<? extends JavaFileObject> fileObjects = Collections.singletonList(javaFileObject);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticsCollector, null, null, fileObjects);
        return task.call();
    }

    String getDiagnostics() {
        return diagnosticsCollector.getDiagnostics().stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    private static class JavaObjectFromString extends SimpleJavaFileObject {
        private String contents;

        JavaObjectFromString(String className, String contents) throws Exception {
            super(new URI(className), Kind.SOURCE);
            this.contents = contents;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return contents;
        }
    }


}
