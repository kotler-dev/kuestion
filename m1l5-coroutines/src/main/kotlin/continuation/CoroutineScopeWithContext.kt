package continuation

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/*

public fun <T> CoroutineScope.async(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T>

val deferred = GlobalScope.async { ... }

*/

fun main() = runBlocking {
    withContext(CoroutineName("My personal context")) {
//    withContext(Dispatchers.IO) {
        val res = async {
            42
        }
        println(this.coroutineContext.toString())
        println(res.await())
    }
}