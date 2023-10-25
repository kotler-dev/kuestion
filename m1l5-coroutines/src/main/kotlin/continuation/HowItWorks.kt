package continuation

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Start")

    val time = measureTimeMillis {
        launch {
            delay(1000)
            repeat(5) {
                delay(500)
                println("Working...")
            }
            println("Done")
        }

        launch {
            delay(1000)
            repeat(5) {
                delay(500)
                println("Working...")
            }
            println("Done")
        }
    }
    println("time: $time")

//    suspendCoroutine<Unit> { continuation ->
////        continuation.resume(Unit)
//    }

    println("Finish")
}

/*
fun main() = runBlocking {
    println("Start")

    launch {
        delay(1000)
        repeat(5) {
            delay(500)
            println("Working...")
        }
        println("Done")
    }

    suspendCoroutine<Unit> { continuation ->
//        continuation.resume(Unit)
    }

    println("Finish")
}*/
