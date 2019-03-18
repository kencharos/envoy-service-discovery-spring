package my.sdtest.backend1

import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.reactor.mono
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class DebugController {

    @GetMapping("/api/debug1")
    fun hello1(@RequestParam(value="name", defaultValue = "Test") name:String):Mono<String> = GlobalScope.mono {
        val chan = ManagedChannelBuilder
                .forAddress("localhost", 6566)
                .usePlaintext()
                .build()

        val stub = Backend1ServiceGrpc.newStub(chan)

        stub.helloBackend1(B1Request.newBuilder().setMessage(name).build()).answer
    }

    @GetMapping("/api/debug")
    fun sulocalGrpcConnectTest():Mono<List<String>> = GlobalScope.mono {
        val chan = ManagedChannelBuilder
                .forAddress("localhost", 6566)
                .usePlaintext()
                .build()

        val stub = Backend1ServiceGrpc.newStub(chan)

        val bidCall = stub.helloBackend1Stream()

        val res = async {

            val list = mutableListOf<String>()
            for (reply in bidCall) {
                println("receive from server: ${reply.answer}")
                list.add(reply.answer)
                if(reply.answer.contains("Fin")) {
                    bidCall.close()
                    chan.shutdown()
                    break
                }
            }
            list
        }

        bidCall.send(B1Request.newBuilder().setMessage("A1").build())
        bidCall.send(B1Request.newBuilder().setMessage("A2").build())
        bidCall.send(B1Request.newBuilder().setMessage("A3").build())
        bidCall.send(B1Request.newBuilder().setMessage("A4").build())
        bidCall.send(B1Request.newBuilder().setMessage("Fin").build())
        res.await()
    }

}