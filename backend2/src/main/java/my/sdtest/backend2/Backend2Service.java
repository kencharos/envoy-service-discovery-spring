package my.sdtest.backend2;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@GRpcService
public class Backend2Service extends ReactorBackend2ServiceGrpc.Backend2ServiceImplBase {

    @Autowired
    private SomeService service;

    @Override
    public Mono<B2Response> helloBackend2(Mono<B2Request> request) {

        return request.map(r -> {

            System.out.println("CALL BACKEND2 at " + Thread.currentThread().getName());

            int currentCount = service.count(r.getMessage());

            try{
                service.someTxProcess(r.getMessage());
            }catch (Exception e) {
                System.out.println("CALL BACKEND2 error " + e.getMessage() );
            }
            int afterCount = service.count(r.getMessage());

           return B2Response.newBuilder()
                            .setAnswer("[Backend2 Hello " + r.getMessage() +":"+ currentCount +"->" + afterCount+" ]").build();

        });
    }

    @Override
    public Flux<B2Response> helloBackend2Stream(Flux<B2Request> request) {
        System.out.println("CALL BACKEND2 at " + Thread.currentThread().getName());
        return request.map(r -> B2Response.newBuilder().setAnswer("[Backend2 Hello " + r.getMessage() + "]").build());
    }
}
