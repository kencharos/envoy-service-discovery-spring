package my.sdtest.controleplane;

import org.lognet.springboot.grpc.GRpcServerBuilderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.envoyproxy.controlplane.cache.SimpleCache;
import io.envoyproxy.controlplane.server.DiscoveryServer;
import io.grpc.ServerBuilder;

@Component
public class GrpcServerConfig extends GRpcServerBuilderConfigurer {

    @Autowired
    SimpleCache<String> cache;

    @Override
    public void configure(ServerBuilder<?> serverBuilder){

        DiscoveryServer discoveryServer = new DiscoveryServer(cache);
        serverBuilder.addService(discoveryServer.getEndpointDiscoveryServiceImpl());
        // add xDS service for need.
    }
}
