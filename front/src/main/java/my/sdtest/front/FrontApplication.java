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

    @Bean
    public ChannelManager backend(@Value("${grpc.host}") String host,
                                   @Value("${grpc.port}") int port) {
        return new ChannelManager(host, port);
    }

}
