package dag.graf.function

class XYFunctionBuilder(private val code: String) : FunctionBuilder() {
    @Throws(Exception::class)
    fun buildXYFunction() = build(::completeXYSource) as XYFunction?

    private fun completeXYSource(className: String) =
            """
package function;
import static java.lang.Math.*;
import java.util.Random;
public class $className extends dag.graf.function.XYFunction{
  public $className(){}
     private Random r = new Random();
     @Override 
     public double compute(double t){
       ${if (code.contains("return")) code else "return $code"};
     }
}
             """

}