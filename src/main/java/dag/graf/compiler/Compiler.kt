package dag.graf.compiler

import java.io.File
import java.io.IOException
import java.net.URI
import javax.tools.*

internal class Compiler {
    private val compiler: JavaCompiler = ToolProvider.getSystemJavaCompiler()
    private val diagnosticsCollector = DiagnosticCollector<JavaFileObject>()
    private val fileManager: StandardJavaFileManager = compiler.getStandardFileManager(diagnosticsCollector, null, null)

    val diagnostics: String
        get() = diagnosticsCollector.diagnostics.joinToString( separator="\n"){ it.toString() }

    @Throws(Exception::class)
    fun compile(contents: String, className: String): Boolean {
        val fileObjects = listOf(JavaObjectFromString(className, contents))
        val task = compiler.getTask(null, fileManager, diagnosticsCollector, null, null, fileObjects)
        return task.call()
    }

    private class JavaObjectFromString internal constructor(private val className : String,private val contents: String) : SimpleJavaFileObject(URI("$className.java"), JavaFileObject.Kind.SOURCE) {
        @Throws(IOException::class)
        override fun getCharContent(ignoreEncodingErrors: Boolean) = contents
    }

    init {
        val outputDir = File("C:\\Users\\dmvv\\IdeaProjects\\graf\\target\\classes").apply { mkdirs() }
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, listOf(outputDir))
    }
}