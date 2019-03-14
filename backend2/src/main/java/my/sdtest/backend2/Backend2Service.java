package my.sdtest.backend2;

import org.lognet.springboot.grpc.GRpcService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@GRpcService
public class Backend2Service extends ReactorBackend2ServiceGrpc.Backend2ServiceImplBase {

    @Override
    public Mono<B2Response> helloBackend2(Mono<B2Request> request) {
        System.out.println("CALL BACKEND2");
        return request.map(r -> B2Response.newBuilder().setAnswer("[Backend2 Hello " + r.getMessage() + "]").build());
    }

    @Override
    public Flux<B2Response> helloBackend2Stream(Flux<B2Request> request) {
        return request.map(r -> B2Response.newBuilder().setAnswer("[Backend2 Hello " + r.getMessage() + "]").build());
    }
}
