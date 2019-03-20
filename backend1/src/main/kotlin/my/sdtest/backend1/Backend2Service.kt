package my.sdtest.backend1

import brave.propagation.CurrentTraceContext
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import my.sdtest.backend2.B2Request
import my.sdtest.backend2.helloBackend2

import org.lognet.springboot.grpc.GRpcService
import kotlin.coroutines.CoroutineContext

@GRpcService
class Backend2Service(val backend2Stub:Backend2StubManager, val ctx: CurrentTraceContext) : Backend1ServiceImplBase() {

    override val coroutineContext: CoroutineContext
        get() = super.coroutineContext + TracingContextElement(ctx)


    override suspend fun helloBackend1(request: B1Request): B1Response {
        println("CALL BACKEND in ${Thread.currentThread().name}")
        val res = backend2Stub.stub().helloBackend2(B2Request.newBuilder().setMessage(request.message).build())

        return B1Response.newBuilder().setAnswer("[Backend1 ${res.answer}]").build()
    }

    override suspend fun helloBackend1Stream(requests: ReceiveChannel<B1Request>) = produce<B1Response> {

        for(req in requests) {
            send(B1Response.newBuilder().setAnswer("Hello1 ${req.message}").build())
        }
    }
}