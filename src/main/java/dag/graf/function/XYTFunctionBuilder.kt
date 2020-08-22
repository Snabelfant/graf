package dag.graf.function

class XYTFunctionBuilder(private val xCode: String, private val yCode: String) : FunctionBuilder() {
    @Throws(Exception::class)
    fun buildXYTFunction() = build(::completeXYTSource) as XYTFunction?

    private fun completeXYTSource(className: String) =
            """
package function;
import static java.lang.Math.*;
import java.util.Random;
public class $className extends dag.graf.function.XYTFunction{
  public $className(){}
     private Random r = new Random();
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