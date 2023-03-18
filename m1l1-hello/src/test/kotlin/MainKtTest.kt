import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MainKtTest {

    @Test
    fun greetingTest() {
        assertEquals("Hello World!", greeting())
        assertNotEquals("Hello world", greeting())
    }
}