import org.junit.Test

class Ex5Generic {

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


    val iAmLambda = { str: String -> println("My name is $str") }

    fun x(): () -> Unit {
        return {
            println("Hello!")
        }
    }

    private fun <T> myArrayListOf(vararg elements: T): ArrayList<T> =
        if (elements.isEmpty()) arrayListOf() else arrayListOf(*elements)

    private fun spreadString(vararg params: String) {
        var r = arrayOf(*params)
        println(r.contentToString())
    }

    private inline fun <reified T> willCompile(data: T) {
        println("Data { $data } -> ${T::class.simpleName}")
    }

    fun numSqrt(num: Int): Double {
        buildString {
            return@numSqrt kotlin.math.sqrt(num.toDouble())
//            return@buildString kotlin.math.sqrt(x = 1.0)
        }
    }


    @Test
    fun genericTest() {

        iAmLambda("Kotler")

        val str: (name: String) -> String = { name: String ->
            buildString {
                append("He")
                append("llo")
                append(" $name")
            }
        }
        println(str("Kotler"))

        println(numSqrt(42))


        println(myArrayListOf("1", "2", "3"))
        println(myArrayListOf(1, 2, 3))
        println(myArrayListOf(1.2, 2.3, 3.4))

        spreadString("a", "b", "c")

        willCompile(1)
        willCompile(1.0)
        willCompile("1")
    }

    @Test
    fun testExceptionInLambda() {
        try {
            { throw RuntimeException("Lambda") }()
        } catch (e: Exception) {
            println("Caught: $e")
        }
    }
}