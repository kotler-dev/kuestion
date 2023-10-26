package continuation

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun main() = runBlocking {
//    coroutineScope {
//        val job = launch { 1/0 }
//        job.join()
//        println("Finished...")
//    }

    coroutineScope {
        val job = launch() {
            supervisorScope { 1/0 }
        }
        job.join()
        println(job.toString())
        println("Finished...")
    }

//    supervisorScope {  }
}