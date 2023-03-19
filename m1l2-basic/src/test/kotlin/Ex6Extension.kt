import kotlin.test.Test
import kotlin.test.assertEquals

class Ex6Extension {

    inline fun <T> T.addly(block: T.() -> Unit): T {
        block(); return this
    }

    @Test
    fun extensionTest() {
        fun String.print() = run { println(this) }

        "42".print()

        val greeting: String.() -> Unit = { println(this) }
        greeting("Hello")
        "Kotler".greeting()

        "Kotler42".addly(greeting)

        with("42") { println(this.length) }
        assertEquals("42", "42".apply { println(this + 8) })
        assertEquals("42", "42".also { println(it + 8) })
        assertEquals("428", "42".run { println(this + 8); this + 8; })
        assertEquals("428", "42".let { println(it + 8); it + 8; })
    }

    private val WORLD = "World"
    private fun buildHello(): String {
        return buildString {
            append("Hello")
            append(" ")
        }.let { it + WORLD }
    }

    @Test
    fun hello() {
        println(buildHello())
        assertEquals("Hello World", buildHello())
    }
}