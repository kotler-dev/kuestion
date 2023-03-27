package builders.java

enum class Drink {
    WATER,
    COFFEE,
    TEA,
    NONE
}

sealed class Meal {
    data class Breakfast(
        val eggs: Int,
        val bacon: Boolean,
        val drink: Drink,
        val title: String
    ) : Meal()

    data class Dinner(
        val title: String
    ) : Meal()
}

class JavaLikeBuilder {
    private var eggs = 0
    private var bacon = false
    private var drink = Drink.NONE
    private var title = ""

    fun withEggs(eggs: Int): JavaLikeBuilder {
        this.eggs = eggs
        return this
    }

    fun withBacon(bacon: Boolean): JavaLikeBuilder {
        this.bacon = bacon
        return this
    }

    fun withDrink(drink: Drink): JavaLikeBuilder {
        this.drink = drink
        return this
    }

    fun withTitle(title: String): JavaLikeBuilder {
        this.title = title
        return this
    }

    fun build(): Meal.Breakfast {
        return Meal.Breakfast(eggs, bacon, drink, title)
    }
}

fun main() {
    val builder = JavaLikeBuilder()
        .withEggs(11)
        .withBacon(true)
        .withDrink(Drink.COFFEE)
        .withTitle("Egg breakfast")

    builder.withBacon(false)
    val meal = builder.build()
    println(meal)
}