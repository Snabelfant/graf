package dag.graf

import dag.graf.function.FunctionBuilder
import dag.graf.function.XYFunction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class FunctionValuesTest {
    lateinit var function: XYFunction
    lateinit var values: FunctionValues

    private fun init(deltaT: Double) {
        function = mock(XYFunction::class.java)
        values = FunctionValues(function, 1.0, 3.9, deltaT)

        `when`(function.computeX(anyDouble())).thenAnswer {
            it.getArgument<Double>(0) - 1
        }

        `when`(function.computeY(anyDouble())).thenAnswer {
            it.getArgument<Double>(0) + 1
        }
    }

    @Test
    fun testFew() {
        init(1.0)
        values.calculate()
        val xys = values.xys
        assertThat(xys).hasSize(3)
        assertThat(xys[0].first).isEqualTo(0.0)
        assertThat(xys[0].second).isEqualTo(2.0)
        assertThat(xys[0].third).isEqualTo(1.0)
        assertThat(xys[2].third).isEqualTo(3.0)
        assertThat(values.minX).isEqualTo(0.0)
        assertThat(values.maxX).isEqualTo(2.0)
        assertThat(values.minY).isEqualTo(2.0)
        assertThat(values.maxY).isEqualTo(4.0)
    }

    @Test
    fun testMany() {
        init(0.00001)
        values.calculate()
        val xys = values.xys
        assertThat(xys).hasSize(290000)
        assertThat(xys[0].first).isEqualTo(0.0)
        assertThat(xys[0].second).isEqualTo(2.0)
        assertThat(xys[0].third).isEqualTo(1.0)
        assertThat(xys[289999].third).isGreaterThan(3.89).isLessThan(3.9)
    }

    @Test
    fun testMany2() {
        val function = FunctionBuilder("t-1", "t+1").build()
        val calculator = FunctionValues(function!!, 1.0, 3.9, 0.000001)
        calculator.calculate()
        val xys = calculator.xys
        assertThat(xys).hasSize(2900000)
        assertThat(xys[0].first).isEqualTo(0.0)
        assertThat(xys[0].second).isEqualTo(2.0)
        assertThat(xys[0].third).isEqualTo(1.0)
        assertThat(xys[2899999].third).isGreaterThan(3.89).isLessThan(3.9)
    }
}