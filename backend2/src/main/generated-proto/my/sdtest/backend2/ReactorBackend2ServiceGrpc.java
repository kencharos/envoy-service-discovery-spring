package my.sdtest.backend2;

import static my.sdtest.backend2.Backend2ServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: backend2.proto")
public final class ReactorBackend2ServiceGrpc {
    private ReactorBackend2ServiceGrpc() {}

    public static ReactorBackend2ServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorBackend2ServiceStub(channel);
    }

    public static final class ReactorBackend2ServiceStub extends io.grpc.stub.AbstractStub<ReactorBackend2ServiceStub> {
        private Backend2ServiceGrpc.Backend2ServiceStub delegateStub;

        private ReactorBackend2ServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = Backend2ServiceGrpc.newStub(channel);
        }

        private ReactorBackend2ServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = Backend2ServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorBackend2ServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorBackend2ServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<my.sdtest.backend2.B2Response> helloBackend2(reactor.core.publisher.Mono<my.sdtest.backend2.B2Request> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::helloBackend2);
        }

        public reactor.core.publisher.Mono<my.sdtest.backend2.B2Response> helloBackend2(my.sdtest.backend2.B2Request reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::helloBackend2);
        }

    }

    public static abstract class Backend2ServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<my.sdtest.backend2.B2Response> helloBackend2(reactor.core.publisher.Mono<my.sdtest.backend2.B2Request> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            my.sdtest.backend2.Backend2ServiceGrpc.getHelloBackend2Method(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            my.sdtest.backend2.B2Request,
                                            my.sdtest.backend2.B2Response>(
                                            this, METHODID_HELLO_BACKEND2)))
                    .build();
        }
    }

    private static final int METHODID_HELLO_BACKEND2 = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final Backend2ServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(Backend2ServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_HELLO_BACKEND2:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((my.sdtest.backend2.B2Request) request,
                            (io.grpc.stub.StreamObserver<my.sdtest.backend2.B2Response>) responseObserver,
                            serviceImpl::helloBackend2);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
