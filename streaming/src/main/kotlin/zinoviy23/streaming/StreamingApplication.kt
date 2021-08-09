@file:OptIn(ExperimentalTime::class)

package zinoviy23.streaming

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

/**
 * Kek
 */
@SpringBootApplication
class StreamingApplication

fun main(args: Array<String>) {
    runApplication<StreamingApplication>(*args)
}

@RestController
@RequestMapping("/streaming")
class MyController {
    @GetMapping("/json", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun streamJson(): Flow<User> {
        return usersFlow()
    }

    @GetMapping("/json1", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun steamJson1(): Flow<User> {
        return usersFlow()
    }

    @GetMapping("/text", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamText(): Flow<User> {
        return usersFlow()
    }

    @GetMapping("/lol{kek}")
    fun kek(@PathVariable kek: String) {

    }

    private fun usersFlow() = flow {
        for (index in generateSequence(0) { it + 1 }) {
            emit(User("user$index", Random.nextInt()))
            delay(Duration.milliseconds(100))
        }
    }

    @GetMapping("/chars", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamChars(): Flow<Char> {
        return flow {
            while (true) {
                emit(Random.nextInt('a'.code .. 'z'.code).toChar())
                delay(Duration.milliseconds(500))
            }
        }
    }
}

data class User(val name: String, val level: Int)
