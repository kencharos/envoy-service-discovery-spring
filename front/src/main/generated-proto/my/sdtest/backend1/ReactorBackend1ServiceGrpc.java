package my.sdtest.backend1;

import static my.sdtest.backend1.Backend1ServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: backend1.proto")
public final class ReactorBackend1ServiceGrpc {
    private ReactorBackend1ServiceGrpc() {}

    public static ReactorBackend1ServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorBackend1ServiceStub(channel);
    }

    /**
     * <pre>
     *  The greeting service definition.
     * <pre>
     */
    public static final class ReactorBackend1ServiceStub extends io.grpc.stub.AbstractStub<ReactorBackend1ServiceStub> {
        private Backend1ServiceGrpc.Backend1ServiceStub delegateStub;

        private ReactorBackend1ServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = Backend1ServiceGrpc.newStub(channel);
        }

        private ReactorBackend1ServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = Backend1ServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorBackend1ServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorBackend1ServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<my.sdtest.backend1.B1Response> helloBackend1(reactor.core.publisher.Mono<my.sdtest.backend1.B1Request> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::helloBackend1);
        }

        public reactor.core.publisher.Flux<my.sdtest.backend1.B1Response> helloBackend1Stream(reactor.core.publisher.Flux<my.sdtest.backend1.B1Request> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.manyToMany(reactorRequest, delegateStub::helloBackend1Stream);
        }

        public reactor.core.publisher.Mono<my.sdtest.backend1.B1Response> helloBackend1(my.sdtest.backend1.B1Request reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::helloBackend1);
        }

    }

    /**
     * <pre>
     *  The greeting service definition.
     * <pre>
     */
    public static abstract class Backend1ServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<my.sdtest.backend1.B1Response> helloBackend1(reactor.core.publisher.Mono<my.sdtest.backend1.B1Request> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Flux<my.sdtest.backend1.B1Response> helloBackend1Stream(reactor.core.publisher.Flux<my.sdtest.backend1.B1Request> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            my.sdtest.backend1.Backend1ServiceGrpc.getHelloBackend1Method(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            my.sdtest.backend1.B1Request,
                                            my.sdtest.backend1.B1Response>(
                                            this, METHODID_HELLO_BACKEND1)))
                    .addMethod(
                            my.sdtest.backend1.Backend1ServiceGrpc.getHelloBackend1StreamMethod(),
                            asyncBidiStreamingCall(
                                    new MethodHandlers<
                                            my.sdtest.backend1.B1Request,
                                            my.sdtest.backend1.B1Response>(
                                            this, METHODID_HELLO_BACKEND1STREAM)))
                    .build();
        }
    }

    private static final int METHODID_HELLO_BACKEND1 = 0;
    private static final int METHODID_HELLO_BACKEND1STREAM = 1;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final Backend1ServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(Backend1ServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_HELLO_BACKEND1:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((my.sdtest.backend1.B1Request) request,
                            (io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Response>) responseObserver,
                            serviceImpl::helloBackend1);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_HELLO_BACKEND1STREAM:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.reactorgrpc.stub.ServerCalls.manyToMany(
                            (io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Response>) responseObserver,
                            serviceImpl::helloBackend1Stream);
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
