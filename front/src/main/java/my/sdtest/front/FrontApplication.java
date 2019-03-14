package my.sdtest.front;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }

    @Bean("backend1")
    public ChannelManager backend1(@Value("${grpc.backend1.host}") String host,
                                   @Value("${grpc.backend1.port}") int port) {
        return new ChannelManager(host, port);
    }

    @Bean("backend2")
    public ChannelManager backend2(@Value("${grpc.backend2.host}") String host,
                                   @Value("${grpc.backend2.port}") int port) {
        return new ChannelManager(host, port);
    }
}
