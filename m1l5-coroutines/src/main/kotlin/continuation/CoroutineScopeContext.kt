package continuation

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.system.measureTimeMillis

fun main() = runBlocking(CoroutineName("context1")) {
    val context = Dispatchers.IO + Job() + CoroutineName("context2")

//    withContext(CoroutineName("context3")) {
    withContext(context) {
        val v1 = async { delay(2000L); 8 }
        val v2 = async { delay(1000L); 42 }
        val time = measureTimeMillis {
            println("v1: ${v1.await()}, v2: ${v2.await()}")
        }
        println("time: $time")
    }
    println("...")
}