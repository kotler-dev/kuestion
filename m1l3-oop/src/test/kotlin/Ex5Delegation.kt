import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstValue(
    private val value: Int
) : ReadWriteProperty<Any, Int> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return value
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        TODO("Not yet implemented")
    }
}

class DelegateExample {
    val constValue by ConstValue(100500)
    val lazyVal by lazy {
        println("calculate...")
        42
    }
}

class Ex5Delegation {
    @Test
    fun test() {
        val example = DelegateExample()

        println(example.constValue)
        assertEquals(example.constValue, 100500)

        println(example.lazyVal)
        assertEquals(example.lazyVal, 42)
    }
}