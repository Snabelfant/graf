package dag.graf

import dag.graf.function.FunctionBuilder
import dag.graf.function.XYFunction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ComputerTest {
    lateinit var function: XYFunction
    lateinit var computer: Computer

    private fun init(deltaT : Double) {
        function = mock(XYFunction::class.java)
        computer = Computer(function, 1.0, 3.9, deltaT)

        `when`(function.computeX(anyDouble())).thenAnswer {
            it.getArgument<Double>(0) - 1
        }

        `when`(function.computeY(anyDouble())).thenAnswer {
            it.getArgument<Double>(0) + 1
        }
    }

    @Test
    fun next() {
        init(1.0)
        assertTrue(computer.hasNext())
        val (x1, y1, t1) = computer.next()
        assertThat(x1).isEqualTo(0.0)
        assertThat(y1).isEqualTo(2.0)
        assertThat(t1).isEqualTo(1.0)

        assertTrue(computer.hasNext())
        val (x2, y2, t2) = computer.next()
        assertThat(x2).isEqualTo(1.0)
        assertThat(y2).isEqualTo(3.0)
        assertThat(t2).isEqualTo(2.0)

        assertTrue(computer.hasNext())
        val (x3, y3, t3) = computer.next()
        assertThat(x3).isEqualTo(2.0)
        assertThat(y3).isEqualTo(4.0)
        assertThat(t3).isEqualTo(3.0)

        assertFalse(computer.hasNext())
    }

    @Test
    fun nextN1() {
        init(1.0)
        val xys = computer.next(2)
        assertThat(xys).hasSize(2)
        assertThat(xys[0].first).isEqualTo(0.0)
        assertThat(xys[0].second).isEqualTo(2.0)
        assertThat(xys[0].third).isEqualTo(1.0)
        assertThat(xys[1].third).isEqualTo(2.0)
    }

    @Test
    fun nextN100() {
        init(1.0)
        val xys = computer.next(100)
        assertThat(xys).hasSize(3)
        assertThat(xys[0].first).isEqualTo(0.0)
        assertThat(xys[0].second).isEqualTo(2.0)
        assertThat(xys[0].third).isEqualTo(1.0)
        assertThat(xys[2].third).isEqualTo(3.0)
    }

    @Test
    fun all() {
        init(1.0)
        val xys = computer.all()
        assertThat(xys).hasSize(3)
        assertThat(xys[0].first).isEqualTo(0.0)
        assertThat(xys[0].second).isEqualTo(2.0)
        assertThat(xys[0].third).isEqualTo(1.0)
        assertThat(xys[2].third).isEqualTo(3.0)
    }

    @Test
    fun all2() {
        init(0.00001)
         val xys = computer.all()
        assertThat(xys).hasSize(290000)
        assertThat(xys[0].first).isEqualTo(0.0)
        assertThat(xys[0].second).isEqualTo(2.0)
        assertThat(xys[0].third).isEqualTo(1.0)
        assertThat(xys[289999].third).isGreaterThan(3.89).isLessThan(3.9)
    }

   @Test
    fun all3() {
       val function = FunctionBuilder("t-1", "t+1").build()
       val computer = Computer(function!!, 1.0,3.9,0.00001)
        init(0.00001)
         val xys = computer.all()
        assertThat(xys).hasSize(290000)
        assertThat(xys[0].first).isEqualTo(0.0)
        assertThat(xys[0].second).isEqualTo(2.0)
        assertThat(xys[0].third).isEqualTo(1.0)
        assertThat(xys[289999].third).isGreaterThan(3.89).isLessThan(3.9)
    }
}