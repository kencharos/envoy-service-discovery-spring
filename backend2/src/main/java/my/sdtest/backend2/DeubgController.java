package my.sdtest.backend2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.grpc.ManagedChannelBuilder;
import reactor.core.publisher.Mono;

@RestController
public class DeubgController {

    @GetMapping("/debug")
    public Mono<String> debug() {

        var chan = ManagedChannelBuilder.forAddress("localhost", 6567).usePlaintext().build();

        var stub = ReactorBackend2ServiceGrpc.newReactorStub(chan);

        return stub.helloBackend2(B2Request.newBuilder().setMessage("debug").build())
            .map(B2Response::getAnswer);

    }

}
