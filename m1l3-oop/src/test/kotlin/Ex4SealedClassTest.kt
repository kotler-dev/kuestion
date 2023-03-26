import kotlin.test.Test
import kotlin.test.assertEquals

sealed interface Base

object ChildA : Base
object ChildB : Base
//class ChildC : Base

class Ex4SealedClassTest {
    @Test
    fun test() {
        val obj: Base = ChildA

        val result = when(obj) {
            is ChildA -> "a"
            is ChildB -> "b"
            else -> throw RuntimeException("Unknown parameter")
        }

        println(result)
        assertEquals(result, "a")
    }
}

