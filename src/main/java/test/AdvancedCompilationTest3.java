package test;

import java.io.*;
import java.net.URI;
import java.util.*;
import javax.tools.*;
import javax.tools.JavaCompiler.*;

public class AdvancedCompilationTest3 {
    public static void main(String[] args) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector diagnosticsCollector =
                new DiagnosticCollector();
        StandardJavaFileManager fileManager =
                compiler.getStandardFileManager(diagnosticsCollector, null, null);
        System.out.println(fileManager.getLocation(StandardLocation.CLASS_OUTPUT));
        File outputDir = new File( "C:\\Users\\dmvv\\IdeaProjects\\graf\\target\\classes");
        outputDir.mkdirs();
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(outputDir));
        final Iterable<? extends File> locations = fileManager.getLocation(StandardLocation.CLASS_OUTPUT);
        locations.forEach(f -> System.out.println(f.getAbsolutePath()));

        JavaFileObject javaObjectFromString = getJavaFileContentsAsString();
        Iterable fileObjects = Arrays.asList(javaObjectFromString);
        CompilationTask task = compiler.getTask(null, fileManager, diagnosticsCollector, null, null, fileObjects);
        Boolean result = task.call();
        List<Diagnostic> diagnostics = diagnosticsCollector.getDiagnostics();
        for (Diagnostic d : diagnostics) {
            System.out.println(d);
        }
        if (result) {
            System.out.println("Compilation has succeeded");
            final Class<?> testClass = Class.forName("test.TestClass");
            final Object o = testClass.getConstructor(). newInstance();
            o.getClass().getMethod("testMethod").invoke(o);
            AdvancedCompilationTest3.class.getClassLoader().loadClass("test.TestClass");

        } else {
            System.out.println("Compilation fails.");
        }
    }

    static SimpleJavaFileObject getJavaFileContentsAsString() {
        StringBuilder javaFileContents = new StringBuilder("" +
                "package test;" +
                "public class TestClass{" +
                "	public void testMethod(){" +
                "		System.out.println(" + "\"test\"" + ");" +
                "}" +
                "}");
        JavaObjectFromString javaFileObject = null;
        try {
            javaFileObject = new JavaObjectFromString("test.TestClass", javaFileContents.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return javaFileObject;
    }

    static class JavaObjectFromString extends SimpleJavaFileObject{
        private String contents = null;
        JavaObjectFromString(String className, String contents) throws Exception{
            super(URI.create("file:///c:/temp/" + className.replace('.','/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.contents = contents;
            System.out.println(URI.create("string:///" + className.replace('.','/') + Kind.SOURCE.extension));
        }
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return contents;
        }
    }
}