package continuation

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = launch(start = CoroutineStart.LAZY) {
        println("Launch.LAZY run")
    }
    job.start()
    println("Hello from coroutineScope!")
}