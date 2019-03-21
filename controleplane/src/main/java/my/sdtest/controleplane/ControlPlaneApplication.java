package my.sdtest.controleplane;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.envoyproxy.controlplane.cache.SimpleCache;

@SpringBootApplication
@GRpcService // fake for auto configure gRPC Boot Starter。
@EnableConfigurationProperties(SomethingValue.class)
public class ControlPlaneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlPlaneApplication.class, args);
    }

    @Bean
    public SimpleCache<String> xdsConfigCache() {

        return new SimpleCache<>(node -> node.getCluster());
    }


}
