package my.sdtest.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import my.sdtest.backend1.B1Request;
import my.sdtest.backend1.ReactorBackend1ServiceGrpc;
import my.sdtest.backend1.ReactorBackend1ServiceGrpc.ReactorBackend1ServiceStub;
import my.sdtest.backend2.B2Request;
import my.sdtest.backend2.ReactorBackend2ServiceGrpc;
import reactor.core.publisher.Mono;

@RestController
public class ApiController {
    @Autowired
    private ChannelManager backend;

    @GetMapping("/api/hello1/{name}")
    public Mono<String> hello1(@PathVariable("name") String name) {

        var stub = ReactorBackend1ServiceGrpc.newReactorStub(backend.get());

        return stub.helloBackend1(B1Request.newBuilder().setMessage(name).build())
                .map(r -> r.getAnswer());
    }


    @GetMapping("/api/hello2/{name}")
    public Mono<String> hello2(@PathVariable("name") String name) {

        var stub = ReactorBackend2ServiceGrpc.newReactorStub(backend.get());

        return stub.helloBackend2(B2Request.newBuilder().setMessage(name).build())
                   .map(r -> r.getAnswer());
    }


}
