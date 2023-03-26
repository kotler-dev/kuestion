interface InterfaceA {
    fun area(): Int
}

interface InterfaceB {
    fun area(): Int
}


abstract class AbstractClass : InterfaceA, InterfaceB {
    abstract override fun area(): Int
}

class AbstractClassImpl : AbstractClass() {
    private var a = 1
    private var b = 2
    override fun area(): Int {
        return a * b
    }
//    override val area: Int
//        get() {
//            return a * b
//        }b
}

fun main() {
    val a = AbstractClassImpl()
    println(a.area())
}