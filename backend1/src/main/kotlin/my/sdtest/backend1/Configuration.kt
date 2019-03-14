package my.sdtest.backend1

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import my.sdtest.backend2.Backend2ServiceGrpc
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class Backend2StubManager(val host:String, val port:Int) {

    private val chan:ManagedChannel by lazy {ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()}

    fun get():ManagedChannel  {
        return chan
    }

    fun stub():Backend2ServiceGrpc.Backend2ServiceStub {
        return Backend2ServiceGrpc.newStub(get())
    }

}

@Configuration
class AppConfiguration {

    @Bean
    fun backend2Client(@Value("\${grpc.backend2.host}") host:String,
                @Value("\${grpc.backend2.port}") port:Int): Backend2StubManager {
        return Backend2StubManager(host, port)
    }


}