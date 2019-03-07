package my.sdtest.backend1

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

import org.lognet.springboot.grpc.GRpcService

@GRpcService
class Backend2Service : Backend1ServiceImplBase() {

    override suspend fun helloBackend1(request: B1Request): B1Response {
        return B1Response.newBuilder().setAnswer("Hello1 ${request.message}").build()
    }

    override suspend fun helloBackend1Stream(requests: ReceiveChannel<B1Request>) = produce<B1Response> {

        for(req in requests) {
            send(B1Response.newBuilder().setAnswer("Hello1 ${req.message}").build())
        }

    }
}