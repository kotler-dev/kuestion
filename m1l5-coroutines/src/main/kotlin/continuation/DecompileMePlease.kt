package continuation

import kotlinx.coroutines.delay

suspend fun myFunction() {
    println("Before")
    var counter = 0
    delay(1000)
    counter++
    println("After: $counter")
}