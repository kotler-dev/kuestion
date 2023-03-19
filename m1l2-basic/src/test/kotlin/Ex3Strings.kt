import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Ex3Strings {

    @Test
    fun strings() {
        val string = "Kotler"
        println(string[0])

        val codeExample = """
            val string = "Kotler"
            println(string[0])
        """.trimIndent()
        println(codeExample)

        val codeExample2 = """
            |val string = "Kotler"
            println(string[0])
        """.trimMargin("|")
        println(codeExample2)
    }

    fun f(): String {
        for (i in 1 .. 3)
            println(i)
        return "hello"
    }

    @Test
    fun templates() {
        val a = 1
        val b = 2
        val string = "$a + $b = ${a+b}"
        assertEquals("1 + 2 = 3", string)

        assertEquals("${"hello, $a"}", "hello, 1")

        // не стоит увлекаться :)
        val c = "${
            when(a) {
                1 -> f()
                else -> "world"
            }
        }"
        assertEquals(c, "hello")
    }

    // Smart cast
    @Test
    fun x() {
        var a: Any = 1
        if (a is String) {
            a.length
        }
    }
}