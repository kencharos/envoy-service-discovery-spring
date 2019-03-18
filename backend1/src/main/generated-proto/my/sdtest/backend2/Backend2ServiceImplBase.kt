package my.sdtest.backend2

import my.sdtest.backend2.Backend2ServiceGrpc.*

import io.grpc.*
import io.grpc.stub.*
import io.rouz.grpc.*

import kotlin.coroutines.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*



@javax.annotation.Generated(
    value = ["by gRPC Kotlin generator"],
    comments = "Source: backend2.proto"
)
abstract class Backend2ServiceImplBase(
    coroutineContext: CoroutineContext = Dispatchers.Default
) : BindableService, CoroutineScope {

    private val _coroutineContext: CoroutineContext = coroutineContext

    override val coroutineContext: CoroutineContext
        get() = ContextCoroutineContextElement() + _coroutineContext

    
    
    
    open suspend fun helloBackend2(request: my.sdtest.backend2.B2Request): my.sdtest.backend2.B2Response {
        throw unimplemented(getHelloBackend2Method()).asRuntimeException()
    }

    internal fun helloBackend2Internal(
        request: my.sdtest.backend2.B2Request,
        responseObserver: StreamObserver<my.sdtest.backend2.B2Response>
    ) {
        launch {
            tryCatchingStatus(responseObserver) {
                val response = helloBackend2(request)
                onNext(response)
            }
        }
    }
    
    
    
    open suspend fun helloBackend2Stream(requests: ReceiveChannel<my.sdtest.backend2.B2Request>): ReceiveChannel<my.sdtest.backend2.B2Response> {
        throw unimplemented(getHelloBackend2StreamMethod()).asRuntimeException()
    }

    internal fun helloBackend2StreamInternal(
        responseObserver: StreamObserver<my.sdtest.backend2.B2Response>
    ): StreamObserver<my.sdtest.backend2.B2Request> {
        val requests = StreamObserverChannel<my.sdtest.backend2.B2Request>()
        launch {
            tryCatchingStatus(responseObserver) {
                val responses = helloBackend2Stream(requests)
                for (response in responses) {
                    onNext(response)
                }
            }
        }
        return requests
    }

    override fun bindService(): ServerServiceDefinition {
        return ServerServiceDefinition.builder(getServiceDescriptor())
            .addMethod(
                getHelloBackend2Method(),
                ServerCalls.asyncUnaryCall(
                    MethodHandlers(METHODID_HELLO_BACKEND2)
                )
            )
            .addMethod(
                getHelloBackend2StreamMethod(),
                ServerCalls.asyncBidiStreamingCall(
                    MethodHandlers(METHODID_HELLO_BACKEND2STREAM)
                )
            )
            .build()
    }

    private fun unimplemented(methodDescriptor: MethodDescriptor<*, *>): Status {
        return Status.UNIMPLEMENTED
            .withDescription("Method ${methodDescriptor.fullMethodName} is unimplemented")
    }

    private fun <E> handleException(t: Throwable?, responseObserver: StreamObserver<E>) {
        when (t) {
            null -> return
            is CancellationException -> handleException(t.cause, responseObserver)
            is StatusException, is StatusRuntimeException -> responseObserver.onError(t)
            is RuntimeException -> {
                responseObserver.onError(Status.UNKNOWN.asRuntimeException())
                throw t
            }
            is Exception -> {
                responseObserver.onError(Status.UNKNOWN.asException())
                throw t
            }
            else -> {
                responseObserver.onError(Status.INTERNAL.asException())
                throw t
            }
        }
    }

    private suspend fun <E> tryCatchingStatus(responseObserver: StreamObserver<E>, body: suspend StreamObserver<E>.() -> Unit) {
        try {
            responseObserver.body()
            responseObserver.onCompleted()
        } catch (t: Throwable) {
            handleException(t, responseObserver)
        }
    }

    private val METHODID_HELLO_BACKEND2 = 0
    private val METHODID_HELLO_BACKEND2STREAM = 1

    private inner class MethodHandlers<Req, Resp> internal constructor(
        private val methodId: Int
    ) : ServerCalls.UnaryMethod<Req, Resp>,
        ServerCalls.ServerStreamingMethod<Req, Resp>,
        ServerCalls.ClientStreamingMethod<Req, Resp>,
        ServerCalls.BidiStreamingMethod<Req, Resp> {

        @Suppress("UNCHECKED_CAST")
        override fun invoke(request: Req, responseObserver: StreamObserver<Resp>) {
            when (methodId) {
                METHODID_HELLO_BACKEND2 ->
                    this@Backend2ServiceImplBase.helloBackend2Internal(
                        request as my.sdtest.backend2.B2Request,
                        responseObserver as StreamObserver<my.sdtest.backend2.B2Response>
                    )
                else -> throw AssertionError()
            }
        }

        @Suppress("UNCHECKED_CAST")
        override fun invoke(responseObserver: StreamObserver<Resp>): StreamObserver<Req> {
            when (methodId) {
                METHODID_HELLO_BACKEND2STREAM ->
                    return this@Backend2ServiceImplBase.helloBackend2StreamInternal(
                        responseObserver as StreamObserver<my.sdtest.backend2.B2Response>
                    ) as StreamObserver<Req>
                else -> throw AssertionError()
            }
        }
    }
}
