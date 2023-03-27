package builders.kotlin

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

class KotlinLikeBuilder {
    var eggs = 0
    var bacon = false
    var drink = Drink.NONE
    var title = ""

    fun build(): Meal.Breakfast {
        return Meal.Breakfast(eggs, bacon, drink, title)
    }
}

/* Variant 1:

fun KotlinLikeBuilder.simple() {
    this.eggs = 42
}

fun breakfast(block: (KotlinLikeBuilder) -> Unit): Meal.Breakfast {
    val builder = KotlinLikeBuilder()
    block(builder)
    return builder.build()
}

fun main() {
//    val builder = KotlinLikeBuilder()
//    builder.title = "Drink.COFFEE"
//    builder.drink = Drink.COFFEE

    val meal = breakfast {
        it.eggs = 12
        it.bacon = true
        it.drink = Drink.TEA
        it.title = "Dinner"
    }

    println(meal)
}*/


fun breakfast(block: KotlinLikeBuilder.() -> Unit): Meal.Breakfast {
    val builder = KotlinLikeBuilder()
    block(builder)
    return builder.build()
}

fun main() {
    val meal = breakfast {
        eggs = 12
        bacon = true
        drink = Drink.TEA
        title = "Dinner"
    }
    println(meal)
}
