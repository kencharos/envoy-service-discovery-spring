package my.sdtest.backend2;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.18.0)",
    comments = "Source: backend2.proto")
public final class Backend2ServiceGrpc {

  private Backend2ServiceGrpc() {}

  public static final String SERVICE_NAME = "my.sdtest.backend2.Backend2Service";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<my.sdtest.backend2.B2Request,
      my.sdtest.backend2.B2Response> getHelloBackend2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HelloBackend2",
      requestType = my.sdtest.backend2.B2Request.class,
      responseType = my.sdtest.backend2.B2Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<my.sdtest.backend2.B2Request,
      my.sdtest.backend2.B2Response> getHelloBackend2Method() {
    io.grpc.MethodDescriptor<my.sdtest.backend2.B2Request, my.sdtest.backend2.B2Response> getHelloBackend2Method;
    if ((getHelloBackend2Method = Backend2ServiceGrpc.getHelloBackend2Method) == null) {
      synchronized (Backend2ServiceGrpc.class) {
        if ((getHelloBackend2Method = Backend2ServiceGrpc.getHelloBackend2Method) == null) {
          Backend2ServiceGrpc.getHelloBackend2Method = getHelloBackend2Method = 
              io.grpc.MethodDescriptor.<my.sdtest.backend2.B2Request, my.sdtest.backend2.B2Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "my.sdtest.backend2.Backend2Service", "HelloBackend2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.sdtest.backend2.B2Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.sdtest.backend2.B2Response.getDefaultInstance()))
                  .setSchemaDescriptor(new Backend2ServiceMethodDescriptorSupplier("HelloBackend2"))
                  .build();
          }
        }
     }
     return getHelloBackend2Method;
  }

  private static volatile io.grpc.MethodDescriptor<my.sdtest.backend2.B2Request,
      my.sdtest.backend2.B2Response> getHelloBackend2StreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HelloBackend2Stream",
      requestType = my.sdtest.backend2.B2Request.class,
      responseType = my.sdtest.backend2.B2Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<my.sdtest.backend2.B2Request,
      my.sdtest.backend2.B2Response> getHelloBackend2StreamMethod() {
    io.grpc.MethodDescriptor<my.sdtest.backend2.B2Request, my.sdtest.backend2.B2Response> getHelloBackend2StreamMethod;
    if ((getHelloBackend2StreamMethod = Backend2ServiceGrpc.getHelloBackend2StreamMethod) == null) {
      synchronized (Backend2ServiceGrpc.class) {
        if ((getHelloBackend2StreamMethod = Backend2ServiceGrpc.getHelloBackend2StreamMethod) == null) {
          Backend2ServiceGrpc.getHelloBackend2StreamMethod = getHelloBackend2StreamMethod = 
              io.grpc.MethodDescriptor.<my.sdtest.backend2.B2Request, my.sdtest.backend2.B2Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "my.sdtest.backend2.Backend2Service", "HelloBackend2Stream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.sdtest.backend2.B2Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.sdtest.backend2.B2Response.getDefaultInstance()))
                  .setSchemaDescriptor(new Backend2ServiceMethodDescriptorSupplier("HelloBackend2Stream"))
                  .build();
          }
        }
     }
     return getHelloBackend2StreamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static Backend2ServiceStub newStub(io.grpc.Channel channel) {
    return new Backend2ServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static Backend2ServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new Backend2ServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static Backend2ServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new Backend2ServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class Backend2ServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void helloBackend2(my.sdtest.backend2.B2Request request,
        io.grpc.stub.StreamObserver<my.sdtest.backend2.B2Response> responseObserver) {
      asyncUnimplementedUnaryCall(getHelloBackend2Method(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<my.sdtest.backend2.B2Request> helloBackend2Stream(
        io.grpc.stub.StreamObserver<my.sdtest.backend2.B2Response> responseObserver) {
      return asyncUnimplementedStreamingCall(getHelloBackend2StreamMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHelloBackend2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                my.sdtest.backend2.B2Request,
                my.sdtest.backend2.B2Response>(
                  this, METHODID_HELLO_BACKEND2)))
          .addMethod(
            getHelloBackend2StreamMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                my.sdtest.backend2.B2Request,
                my.sdtest.backend2.B2Response>(
                  this, METHODID_HELLO_BACKEND2STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class Backend2ServiceStub extends io.grpc.stub.AbstractStub<Backend2ServiceStub> {
    private Backend2ServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Backend2ServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Backend2ServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Backend2ServiceStub(channel, callOptions);
    }

    /**
     */
    public void helloBackend2(my.sdtest.backend2.B2Request request,
        io.grpc.stub.StreamObserver<my.sdtest.backend2.B2Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHelloBackend2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<my.sdtest.backend2.B2Request> helloBackend2Stream(
        io.grpc.stub.StreamObserver<my.sdtest.backend2.B2Response> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getHelloBackend2StreamMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class Backend2ServiceBlockingStub extends io.grpc.stub.AbstractStub<Backend2ServiceBlockingStub> {
    private Backend2ServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Backend2ServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Backend2ServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Backend2ServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public my.sdtest.backend2.B2Response helloBackend2(my.sdtest.backend2.B2Request request) {
      return blockingUnaryCall(
          getChannel(), getHelloBackend2Method(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class Backend2ServiceFutureStub extends io.grpc.stub.AbstractStub<Backend2ServiceFutureStub> {
    private Backend2ServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Backend2ServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Backend2ServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Backend2ServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<my.sdtest.backend2.B2Response> helloBackend2(
        my.sdtest.backend2.B2Request request) {
      return futureUnaryCall(
          getChannel().newCall(getHelloBackend2Method(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HELLO_BACKEND2 = 0;
  private static final int METHODID_HELLO_BACKEND2STREAM = 1;

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
          serviceImpl.helloBackend2((my.sdtest.backend2.B2Request) request,
              (io.grpc.stub.StreamObserver<my.sdtest.backend2.B2Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HELLO_BACKEND2STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.helloBackend2Stream(
              (io.grpc.stub.StreamObserver<my.sdtest.backend2.B2Response>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class Backend2ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    Backend2ServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return my.sdtest.backend2.Backend2.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Backend2Service");
    }
  }

  private static final class Backend2ServiceFileDescriptorSupplier
      extends Backend2ServiceBaseDescriptorSupplier {
    Backend2ServiceFileDescriptorSupplier() {}
  }

  private static final class Backend2ServiceMethodDescriptorSupplier
      extends Backend2ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    Backend2ServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (Backend2ServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new Backend2ServiceFileDescriptorSupplier())
              .addMethod(getHelloBackend2Method())
              .addMethod(getHelloBackend2StreamMethod())
              .build();
        }
      }
    }
    return result;
  }
}
