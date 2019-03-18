package my.sdtest.backend1;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.18.0)",
    comments = "Source: backend1.proto")
public final class Backend1ServiceGrpc {

  private Backend1ServiceGrpc() {}

  public static final String SERVICE_NAME = "my.sdtest.backend1.Backend1Service";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<my.sdtest.backend1.B1Request,
      my.sdtest.backend1.B1Response> getHelloBackend1Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HelloBackend1",
      requestType = my.sdtest.backend1.B1Request.class,
      responseType = my.sdtest.backend1.B1Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<my.sdtest.backend1.B1Request,
      my.sdtest.backend1.B1Response> getHelloBackend1Method() {
    io.grpc.MethodDescriptor<my.sdtest.backend1.B1Request, my.sdtest.backend1.B1Response> getHelloBackend1Method;
    if ((getHelloBackend1Method = Backend1ServiceGrpc.getHelloBackend1Method) == null) {
      synchronized (Backend1ServiceGrpc.class) {
        if ((getHelloBackend1Method = Backend1ServiceGrpc.getHelloBackend1Method) == null) {
          Backend1ServiceGrpc.getHelloBackend1Method = getHelloBackend1Method = 
              io.grpc.MethodDescriptor.<my.sdtest.backend1.B1Request, my.sdtest.backend1.B1Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "my.sdtest.backend1.Backend1Service", "HelloBackend1"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.sdtest.backend1.B1Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.sdtest.backend1.B1Response.getDefaultInstance()))
                  .setSchemaDescriptor(new Backend1ServiceMethodDescriptorSupplier("HelloBackend1"))
                  .build();
          }
        }
     }
     return getHelloBackend1Method;
  }

  private static volatile io.grpc.MethodDescriptor<my.sdtest.backend1.B1Request,
      my.sdtest.backend1.B1Response> getHelloBackend1StreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HelloBackend1Stream",
      requestType = my.sdtest.backend1.B1Request.class,
      responseType = my.sdtest.backend1.B1Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<my.sdtest.backend1.B1Request,
      my.sdtest.backend1.B1Response> getHelloBackend1StreamMethod() {
    io.grpc.MethodDescriptor<my.sdtest.backend1.B1Request, my.sdtest.backend1.B1Response> getHelloBackend1StreamMethod;
    if ((getHelloBackend1StreamMethod = Backend1ServiceGrpc.getHelloBackend1StreamMethod) == null) {
      synchronized (Backend1ServiceGrpc.class) {
        if ((getHelloBackend1StreamMethod = Backend1ServiceGrpc.getHelloBackend1StreamMethod) == null) {
          Backend1ServiceGrpc.getHelloBackend1StreamMethod = getHelloBackend1StreamMethod = 
              io.grpc.MethodDescriptor.<my.sdtest.backend1.B1Request, my.sdtest.backend1.B1Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "my.sdtest.backend1.Backend1Service", "HelloBackend1Stream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.sdtest.backend1.B1Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.sdtest.backend1.B1Response.getDefaultInstance()))
                  .setSchemaDescriptor(new Backend1ServiceMethodDescriptorSupplier("HelloBackend1Stream"))
                  .build();
          }
        }
     }
     return getHelloBackend1StreamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static Backend1ServiceStub newStub(io.grpc.Channel channel) {
    return new Backend1ServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static Backend1ServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new Backend1ServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static Backend1ServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new Backend1ServiceFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class Backend1ServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void helloBackend1(my.sdtest.backend1.B1Request request,
        io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Response> responseObserver) {
      asyncUnimplementedUnaryCall(getHelloBackend1Method(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Request> helloBackend1Stream(
        io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Response> responseObserver) {
      return asyncUnimplementedStreamingCall(getHelloBackend1StreamMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHelloBackend1Method(),
            asyncUnaryCall(
              new MethodHandlers<
                my.sdtest.backend1.B1Request,
                my.sdtest.backend1.B1Response>(
                  this, METHODID_HELLO_BACKEND1)))
          .addMethod(
            getHelloBackend1StreamMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                my.sdtest.backend1.B1Request,
                my.sdtest.backend1.B1Response>(
                  this, METHODID_HELLO_BACKEND1STREAM)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class Backend1ServiceStub extends io.grpc.stub.AbstractStub<Backend1ServiceStub> {
    private Backend1ServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Backend1ServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Backend1ServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Backend1ServiceStub(channel, callOptions);
    }

    /**
     */
    public void helloBackend1(my.sdtest.backend1.B1Request request,
        io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHelloBackend1Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Request> helloBackend1Stream(
        io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Response> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getHelloBackend1StreamMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class Backend1ServiceBlockingStub extends io.grpc.stub.AbstractStub<Backend1ServiceBlockingStub> {
    private Backend1ServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Backend1ServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Backend1ServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Backend1ServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public my.sdtest.backend1.B1Response helloBackend1(my.sdtest.backend1.B1Request request) {
      return blockingUnaryCall(
          getChannel(), getHelloBackend1Method(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class Backend1ServiceFutureStub extends io.grpc.stub.AbstractStub<Backend1ServiceFutureStub> {
    private Backend1ServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Backend1ServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Backend1ServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Backend1ServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<my.sdtest.backend1.B1Response> helloBackend1(
        my.sdtest.backend1.B1Request request) {
      return futureUnaryCall(
          getChannel().newCall(getHelloBackend1Method(), getCallOptions()), request);
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
          serviceImpl.helloBackend1((my.sdtest.backend1.B1Request) request,
              (io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Response>) responseObserver);
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
        case METHODID_HELLO_BACKEND1STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.helloBackend1Stream(
              (io.grpc.stub.StreamObserver<my.sdtest.backend1.B1Response>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class Backend1ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    Backend1ServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return my.sdtest.backend1.Backend1.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Backend1Service");
    }
  }

  private static final class Backend1ServiceFileDescriptorSupplier
      extends Backend1ServiceBaseDescriptorSupplier {
    Backend1ServiceFileDescriptorSupplier() {}
  }

  private static final class Backend1ServiceMethodDescriptorSupplier
      extends Backend1ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    Backend1ServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (Backend1ServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new Backend1ServiceFileDescriptorSupplier())
              .addMethod(getHelloBackend1Method())
              .addMethod(getHelloBackend1StreamMethod())
              .build();
        }
      }
    }
    return result;
  }
}
