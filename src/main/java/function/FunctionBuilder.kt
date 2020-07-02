package function

class FunctionBuilder( private val xCode: String, private val yCode: String) {
    var diagnostics: String? = null
        private set

    private val compiler = FunctionCompiler()

    @Throws(Exception::class)
    fun build(): Function? {
        val className = "Z${System.currentTimeMillis()}"
        val success = compiler.compile(completeSource(className))
        return if (success) {
            val cls = Class.forName("function.$className")
            println(cls.declaredMethods.map { m -> m.name })
            cls.getConstructor().newInstance() as Function
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
class $className extends Function{
  public $className(){}
     @Override 
     public double computeX(double t){
       $xCode;
     }
     
     @Override
     public double computeY(double t){
       $yCode;
     }
}
             """

}