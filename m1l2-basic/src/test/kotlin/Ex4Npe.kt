import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class Ex4Npe {

    private fun Any.getType(): Any {
        return when (this) {
            is Int -> Int
            is String -> String
            is Float -> Float
            is Double -> Double
            is Long -> Long
            else -> throw RuntimeException("Unknown type")
        }
    }

    @Test
    fun npeTest() {
        val notNullableString: String? = null
        println(notNullableString?.length ?: "Empty string")

        var nullable: String? = null
        assertThrows(NullPointerException::class.java) {
            var length = nullable!!.length
            println(length)
        }

        assertFailsWith<ArrayIndexOutOfBoundsException>(
            message = "No exception found",
            block = {
                val array = intArrayOf(1, 2, 3)
                array[5]
            }
        )

        var nullableContainer = null

        val whatTypeIAm  = nullableContainer?.toDouble()
        assertEquals(null, whatTypeIAm?.getType())

        val guessType = whatTypeIAm ?: 4.6
        assertEquals(Double, guessType.getType())
        assertEquals(4.6, guessType)

        val guessType2 = nullableContainer?.toLong() ?: 5
        assertEquals(Long, guessType2.getType())
        assertEquals(5, guessType2)

        val guessType3 = guessType?.toInt() ?: 2
        assertEquals(Int, guessType3.getType())
        assertEquals(4, guessType3)

        var anyType: Any = 12
        val castFromAny = anyType as? String // Cannot cast Int to String
        assertEquals(null, castFromAny?.getType())
    }
}