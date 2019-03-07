package my.sdtest.backend1

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import my.sdtest.backend2.Backend2ServiceGrpc
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class AppConfiguration {

   // @Bean
    fun channel(@Value("\${grpc.backend2.host}") host:String,
                @Value("\${grpc.backend2.port}") port:Int): ManagedChannel {
        val c = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build()
        return c
    }

    //@Bean
    fun backend2Stub(channel: ManagedChannel): Backend2ServiceGrpc.Backend2ServiceStub{
        return Backend2ServiceGrpc.newStub(channel);
    }

}