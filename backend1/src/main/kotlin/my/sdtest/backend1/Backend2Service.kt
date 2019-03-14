package my.sdtest.backend1

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import my.sdtest.backend2.B2Request
import my.sdtest.backend2.helloBackend2

import org.lognet.springboot.grpc.GRpcService

@GRpcService
class Backend2Service(val backend2Stub:Backend2StubManager) : Backend1ServiceImplBase() {

    override suspend fun helloBackend1(request: B1Request): B1Response {

        val res = backend2Stub.stub().helloBackend2(B2Request.newBuilder().setMessage(request.message).build())

        return B1Response.newBuilder().setAnswer("[Hello wrraped ${res.answer}").build()
    }

    override suspend fun helloBackend1Stream(requests: ReceiveChannel<B1Request>) = produce<B1Response> {

        for(req in requests) {
            send(B1Response.newBuilder().setAnswer("Hello1 ${req.message}").build())
        }

    }
}