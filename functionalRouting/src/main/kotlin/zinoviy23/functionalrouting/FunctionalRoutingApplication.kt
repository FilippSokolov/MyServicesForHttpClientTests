package zinoviy23.functionalrouting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import java.util.function.Supplier

@SpringBootApplication
class FunctionalRoutingApplication

fun main(args: Array<String>) {
    runApplication<FunctionalRoutingApplication>(*args)
}

@Configuration
class MyConfiguration {
    fun foo(arg: String) {

    }

    @Bean
    fun getRouting(): RouterFunction<ServerResponse> {
        foo("Kek")
        return route().nest(path("/product"), Supplier {
            route().GET("name") {
                ok().body(fromValue("Kek"))
            }.build()
        }).build()
    }
}
