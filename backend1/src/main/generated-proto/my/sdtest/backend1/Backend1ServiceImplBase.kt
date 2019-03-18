package my.sdtest.backend1

import my.sdtest.backend1.Backend1ServiceGrpc.*

import io.grpc.*
import io.grpc.stub.*
import io.rouz.grpc.*

import kotlin.coroutines.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
     * <pre>
     *  The greeting service definition.
     * <pre>
     */

@javax.annotation.Generated(
    value = ["by gRPC Kotlin generator"],
    comments = "Source: backend1.proto"
)
abstract class Backend1ServiceImplBase(
    coroutineContext: CoroutineContext = Dispatchers.Default
) : BindableService, CoroutineScope {

    private val _coroutineContext: CoroutineContext = coroutineContext

    override val coroutineContext: CoroutineContext
        get() = ContextCoroutineContextElement() + _coroutineContext

    
    
    
    open suspend fun helloBackend1(request: my.sdtest.backend1.B1Request): my.sdtest.backend1.B1Response {
        throw unimplemented(getHelloBackend1Method()).asRuntimeException()
    }

    internal fun helloBackend1Internal(
        request: my.sdtest.backend1.B1Request,
        responseObserver: StreamObserver<my.sdtest.backend1.B1Response>
    ) {
        launch {
            tryCatchingStatus(responseObserver) {
                val response = helloBackend1(request)
                onNext(response)
            }
        }
    }
    
    
    
    open suspend fun helloBackend1Stream(requests: ReceiveChannel<my.sdtest.backend1.B1Request>): ReceiveChannel<my.sdtest.backend1.B1Response> {
        throw unimplemented(getHelloBackend1StreamMethod()).asRuntimeException()
    }

    internal fun helloBackend1StreamInternal(
        responseObserver: StreamObserver<my.sdtest.backend1.B1Response>
    ): StreamObserver<my.sdtest.backend1.B1Request> {
        val requests = StreamObserverChannel<my.sdtest.backend1.B1Request>()
        launch {
            tryCatchingStatus(responseObserver) {
                val responses = helloBackend1Stream(requests)
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
                getHelloBackend1Method(),
                ServerCalls.asyncUnaryCall(
                    MethodHandlers(METHODID_HELLO_BACKEND1)
                )
            )
            .addMethod(
                getHelloBackend1StreamMethod(),
                ServerCalls.asyncBidiStreamingCall(
                    MethodHandlers(METHODID_HELLO_BACKEND1STREAM)
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

    private val METHODID_HELLO_BACKEND1 = 0
    private val METHODID_HELLO_BACKEND1STREAM = 1

    private inner class MethodHandlers<Req, Resp> internal constructor(
        private val methodId: Int
    ) : ServerCalls.UnaryMethod<Req, Resp>,
        ServerCalls.ServerStreamingMethod<Req, Resp>,
        ServerCalls.ClientStreamingMethod<Req, Resp>,
        ServerCalls.BidiStreamingMethod<Req, Resp> {

        @Suppress("UNCHECKED_CAST")
        override fun invoke(request: Req, responseObserver: StreamObserver<Resp>) {
            when (methodId) {
                METHODID_HELLO_BACKEND1 ->
                    this@Backend1ServiceImplBase.helloBackend1Internal(
                        request as my.sdtest.backend1.B1Request,
                        responseObserver as StreamObserver<my.sdtest.backend1.B1Response>
                    )
                else -> throw AssertionError()
            }
        }

        @Suppress("UNCHECKED_CAST")
        override fun invoke(responseObserver: StreamObserver<Resp>): StreamObserver<Req> {
            when (methodId) {
                METHODID_HELLO_BACKEND1STREAM ->
                    return this@Backend1ServiceImplBase.helloBackend1StreamInternal(
                        responseObserver as StreamObserver<my.sdtest.backend1.B1Response>
                    ) as StreamObserver<Req>
                else -> throw AssertionError()
            }
        }
    }
}
