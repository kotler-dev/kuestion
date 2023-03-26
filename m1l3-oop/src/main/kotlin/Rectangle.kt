open class Rectangle(var width: Int, var height: Int) : Figure {
    override fun toString(): String {
        return "Rectangle(${width}x${height})"
    }

    override fun area(): Int {
        return width * height
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rectangle

        if (width != other.width) return false
        if (height != other.height) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        return result
    }
}