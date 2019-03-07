package my.sdtest.backend1

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import my.sdtest.backend2.Backend2ServiceGrpc
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Backend1Application

fun main(args: Array<String>) {
    runApplication<Backend1Application>(*args)
}

