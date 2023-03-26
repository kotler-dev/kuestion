class Square(var square: Int) : Figure {
    override fun area(): Int {
        return square * square
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Square

        if (square != other.square) return false

        return true
    }

    override fun hashCode(): Int {
        return square
    }

    override fun toString(): String {
        return "Square(square=$square)"
    }
}