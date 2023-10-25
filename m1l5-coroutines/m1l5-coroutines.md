# Coroutines

- Корутины и suspend-функции
- Билдеры корутин
- Structured concurrency
- Рефлексия

Методы синхронизации потоков

Процесс (независимый стэк, независимая память)

- Независимый объект
- Свои системные ресурсы (процессорное время и память)
- Свое адресное пространство
- Один процесс не может получить доступ к данным другого

Мьютексы/монитор (свободен/занят)

Поток (независимый стэк, общая память для всех потоков)

- Маленькая часть процесса
- Поток делят между собой часть адресного пространства процесса
- Свои системные ресурсы (процессорное время и память)
- Каждый поток имеет свой стек и регистры памяти, но другие потоки могут получить к ним доступ

Семафоры (счетчик)

Для доступа к общему ресурсу потока необходим метод синхронизации данных

## Корутины

Корутина - компонент программы, обобщающий понятие подпрограммы, который поддерживает множество входны/выходных точек,
остановку и продолжение выполнения с сохранением определенного состояния.

```kotlin
// определение suspend-функции
suspend fun foo() {
    delay(1000)
}

// suspend-функции как аргумент в сигнатуре
fun bar(block: suspend () -> Int) {
    ...
}

// определение suspend-лямбды
val lambda = suspend { ... }

// использование функции с аргументом suspend-функции
bar { ... }
```

## Continuation (прерывание)

```kotlin
import kotlin.coroutines.CoroutineContext

interface Continuation<in T> {
    val context: CoroutineContext

    fun resumeWith(result: Result<T>)
}
```

Отвечает за прерывание и возобновления работы.

suspendCoroutine призвана преобразовать из асинхронных методов (Futere/ComplitableFuter/Promis) в корутины преобразуются

Корутина (suspend) может мигрировать из одного потока в другой поток.

## Создание и запуск корутин

**CoroutineContext** состоит из:

- `Job` управляет жизненным циклом корутины
- `CoroutineDispatcher` отправляет работу в соответствующий поток
- `CoroutineName` имя корутины, полезно знать для отладки
- `CoroutineExceptionHandler` обрабатывает не отловленные исключения

## Запуск из блокирующего кода

**runBlocking** - coroutine билдер

- занимается запуском/созданием среды для работы корутин
- используемый в качестве моста между блокирующим кодом и корутинами
- возвращается результат выполнения заданного блока
- блокирует поток
- можно использовать для main функции, тест функций
- доступен на платформах JVM, Native
- недоступен в JS
- можно задавать контекст явно

```kotlin
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public fun <T> runBlocking(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
): T

fun main(): Unit = runBlocking {
    println("Hello")
    delay(1000)
    println("World!")
}
```

## Запуск параллельной задачи

**launch** - неблокирующий coroutine билдер, для создания и запуска корутин

- не блокирует поток
- не прерывает корутину

```kotlin
import kotlin.coroutines.coroutineContext

suspend fun main(): Unit = coroutineScope {
    launch { ... }
    println("Need coroutineScope")
}
```

```kotlin
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public fun CoroutineScope.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job

val job = GlobalScope.launch { ... }
```

В CoroutineStart enum перечисление вариантов запуска (ATOMIC, DEFAULT, LAZY, UNDISPATCHED)

- LAZY корутина не запустится и ее нужно будет запустить вручную

```kotlin
suspend fun main(): Unit = coroutineScope {
    val job = launch(start = CoroutineStart.LAZY) {
        println("Launch.LAZY run")
    }
    job.start()
    println("Hello from coroutineScope!")
}
```

GlobalScope для запуска блокирующего кода в любом месте. Деликатное АПИ, которое нужно использовать с осторожностью.

```kotlin
suspend fun main() {
    GlobalScope.launch(start = CoroutineStart.LAZY) {
        println("Launch.LAZY run")
    }
    job.start()
    println("Hello from coroutineScope!")
}
```

## Запуск параллельной задачи

**async** - неблокирующий корутин билдер, для создания и запуска корутин, возвращающих значение

- не блокирует поток
- не прерывает корутину

```kotlin
public fun <T> CoroutineScope.async(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T>

val deferred = GlobalScope.async { ... } 
```

Deferred - некий аналог Future.

## Смена контекста выполнения

**withContext** - корутина билдер для создания и запуска корутин, контекст которых получен из слияния текущего и
заданного аргументом контексто.

- возвращает результат выполнения заданного блока
- прерывает корутину
- ожидает завершения созданной корутины

```kotlin
public suspend fun <T> withContext(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): T

withContext(CoroutineName("My awesome context...")) 
```

## Ограничение области выполнения

**coroutineScope** - корутин билдер для создания и запуска в ограниченной области выполнения

- возвращает результат выполнения заданного блока
- прерывает корутину
- ожидает завершения созданной корутины

```kotlin
public suspend fun <R> coroutineScope(
    block: suspend CoroutineScope.() -> R
): R

coroutineScope { ... } 
```

`supervisorScope {}` позволяет отловить эксепшены для предотвращения падения все цепочки coroutineScope

## Coroutine builders

- `runBlocking` из блокирующего в корутины
- `launch` запускает не блокируя и не прерывая
- `async` запускает не блокируя и не прерывая, можно получить результат
- `withContext` позволяет сменить контекст
- `coroutineScope` создает новую ограниченную область выполнения
- `runTest` для тестов со сжатием времени

## Формирование дочернего контекста

## Диспетчеры

Диспетчер корутин может ограничить выполнение корутины определенным потоком

- `Dispatcher.Default` для выполнения по умолчанию
- `Dispatcher.IO` для выполнения блокирующего кода
- `Dispatcher.Main` для безопасной работы с GUI
- `Dispatcher.Unconfined` для тестовых нужд (без первичного определения используемого потока)
- `ExecutorCoroutineDispatcher` прямиком из Java

**ExecutorCoroutineDispatcher**

- `asCoroutineDispatcher` если нужен свой пул потоков `Executor { ... }.asCoroutineDispatcher()`
- `newSingleThreadContext` - delicate API `newSingleThreadContext().use { ... }.join()`
- `newFixedThreadContext` - delicate API `newFixedThreadContext(nThreads: value).use { ... }.join()`

Рекомендуется использовать новый подход для пула потоков.  
withContext(Dispatcher.IO.limitedParallelism(parallelism: 8) `^runBlocking`)

## Рефлексия

