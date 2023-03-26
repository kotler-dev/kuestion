import org.junit.Test

class Ex2Objects {

    companion object {
        init {
            println("Companion inited")
        }

        fun doSmth() {
            println("Companion object")
        }
    }

    object A {
        init {
            println("A inited")
        }

        fun doSmth() {
            println("Object A")
        }
    }
}

class ObjectsTest {
    @Test
    fun checkObjectsTest() {
        Ex2Objects()
        Ex2Objects()
        Ex2Objects.doSmth()
        Ex2Objects.A.doSmth()
        Ex2Objects.A.doSmth()
        Ex2Objects.A.doSmth()
    }
}