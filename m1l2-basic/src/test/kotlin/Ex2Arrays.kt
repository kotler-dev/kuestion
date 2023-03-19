import kotlin.test.Test
import kotlin.test.assertEquals

class Ex2Arrays {

    @Test
    fun arrays() {
        val arrayOfInt = arrayOf(1, 2, 3)
        println(arrayOfInt)
        println(arrayOfInt.contentToString())

        val emptyArrays = emptyArray<Int>() // arrayOf()
        println(emptyArrays)
        println(emptyArrays.contentToString())

        val arrayCalculated = Array(5) { i -> (i * i) }
        println(arrayCalculated.contentToString())

        val intArray = intArrayOf(1, 2, 3)
        println(intArray.contentToString())

        val element = arrayOfInt[2]
        val elementByFunction = arrayOfInt.get(2) // 3
        assertEquals(element, elementByFunction)
    }
}