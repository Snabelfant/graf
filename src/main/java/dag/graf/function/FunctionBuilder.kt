package dag.graf.function

import dag.graf.compiler.Compiler

class FunctionBuilder( private val xCode: String, private val yCode: String) {
    var diagnostics: String? = null
        private set

    private val compiler = Compiler()

    @Throws(Exception::class)
    fun build(): XYFunction? {
        val className = "Z${System.currentTimeMillis()}"
        val success = compiler.compile(completeSource(className), className)
        return if (success) {
            val cls = Class.forName("function.$className")
            println(cls.declaredMethods.map { it.name })
            println(cls.declaredConstructors.map { it.name + "/" + it.modifiers })
            cls.getConstructor().newInstance() as XYFunction
        } else {
            diagnostics = compiler.diagnostics
            println(diagnostics)
            null
        }
    }

    fun completeSource(className: String) =
            """
package function;
import static java.lang.Math.*;
public class $className extends dag.graf.function.XYFunction{
  public $className(){}
     @Override 
     public double computeX(double t){
       ${if (xCode.contains("return")) xCode else "return $xCode"};
     }
     
     @Override
     public double computeY(double t){
       ${if (yCode.contains("return")) yCode else "return $yCode"};
     }
}
             """

}