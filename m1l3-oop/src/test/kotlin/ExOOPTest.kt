import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals

class ExOOPTest {

    // задание 1 - сделать класс Rectangle, у которого будет width и height
    // а также метод вычисления площади - area()
    @Test
    fun rectangleArea() {
        val r = Rectangle(10, 20)
        assertEquals(200, r.area())
        assertEquals(10, r.width)
        assertEquals(20, r.height)
    }

    // задание 2 - сделать метод Rectangle.toString()
    @Test
    fun rectangleToString() {
        val r = Rectangle(10, 20)
        assertEquals("Rectangle(10x20)", r.toString())
    }

    // задание 3 - сделать методы Rectangle.equals() и Rectangle.hashCode()
    @Test
    fun rectangleEquals() {
        val a = Rectangle(10, 20)
        val b = Rectangle(10, 20)
        val c = Rectangle(20, 10)
        assertEquals(a, b)
        assertEquals(a.hashCode(), b.hashCode())
        assertFalse(a === b)
        assertNotEquals(a, c)
    }

    // задание 4 - сделать класс Square
    @Test
    fun squareEquals() {
        val a = Square(10)
        val b = Square(10)
        val c = Square(20)
        assertEquals(a, b)
        assertEquals(a.hashCode(), b.hashCode())
        assertFalse(a === b)
        assertNotEquals(a, c)
        println(a)
    }

    // задание 5 - сделать интерфейс Figure c методом area(), отнаследуйте от него Rectangle и Square
    @Test
    fun figureArea() {
        var f: Figure = Rectangle(10, 20)
        assertEquals(f.area(), 200)
        f = Square(10)
        assertEquals(f.area(), 100)
    }

    // задание 6 - сделать метод diffArea(a, b)
    @Test
    fun diffAreaTest() {
        val a = Rectangle(10, 20)
        val b = Square(10)
        assertEquals(diffArea(a, b), 100)
    }

    private fun diffArea(a: Figure, b: Figure): Int {
        return a.area() - b.area()
    }
}

