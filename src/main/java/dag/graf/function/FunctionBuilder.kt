package dag.graf.function

import dag.graf.compiler.Compiler

abstract class FunctionBuilder() {
    var diagnostics: String? = null
        private set

    private val compiler = Compiler()

    @Throws(Exception::class)
    protected fun build(buildSource: (String) -> String): Any? {
        val className = "Z${System.currentTimeMillis()}"
        val success = compiler.compile(buildSource(className), className)
        return if (success) {
            val cls = Class.forName("function.$className")
            println(cls.declaredMethods.map { it.name })
            println(cls.declaredConstructors.map { it.name + "/" + it.modifiers })
            cls.getConstructor().newInstance()
        } else {
            diagnostics = compiler.diagnostics
            println(diagnostics)
            null
        }
    }
}