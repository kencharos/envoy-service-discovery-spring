package my.sdtest.controleplane;

import com.google.common.collect.ImmutableList;
import com.google.protobuf.Duration;
import io.envoyproxy.controlplane.cache.SimpleCache;
import io.envoyproxy.controlplane.cache.Snapshot;
import io.envoyproxy.controlplane.server.DiscoveryServer;
import io.envoyproxy.envoy.api.v2.Cluster;
import io.envoyproxy.envoy.api.v2.Cluster.DiscoveryType;
import io.envoyproxy.envoy.api.v2.ClusterLoadAssignment;
import io.envoyproxy.envoy.api.v2.core.Address;
import io.envoyproxy.envoy.api.v2.core.SocketAddress;
import io.envoyproxy.envoy.api.v2.endpoint.Endpoint;
import io.envoyproxy.envoy.api.v2.endpoint.LbEndpoint;
import io.envoyproxy.envoy.api.v2.endpoint.LocalityLbEndpoints;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import java.net.InetSocketAddress;

public class TestMain {

  private static final String GROUP = "key";

  private static Endpoint endpoint(String host, int port) {
    return Endpoint.newBuilder().setAddress(
            Address.newBuilder().setSocketAddress(
                    SocketAddress.newBuilder().setAddress(host).setPortValue(port)).build()
    ).build();
  }


  /**
   * Example minimal xDS implementation using the java-control-plane lib.
   *
   * @param arg command-line args
   */
  public static void main(String[] arg) throws IOException, InterruptedException {
    SimpleCache<String> cache = new SimpleCache<>(node -> {
      System.out.println(node.getCluster());
      System.out.println(node.getId());
      return GROUP;
    });

    cache.setSnapshot(
        GROUP,
        Snapshot.create(
            ImmutableList.of(),
            ImmutableList.of(
                    ClusterLoadAssignment.newBuilder()
                                         .setClusterName("backend1")
                                         .addEndpoints(
                                                 LocalityLbEndpoints.newBuilder().addLbEndpoints(
                                                 LbEndpoint.newBuilder().setEndpoint(endpoint("127.0.0.1", 2000))))
                                         .build(),
                    ClusterLoadAssignment.newBuilder()
                                         .setClusterName("backend2")
                                         .addEndpoints(
                                                 LocalityLbEndpoints.newBuilder().addLbEndpoints(
                                                         LbEndpoint.newBuilder().setEndpoint(endpoint("127.0.0.1", 2000))))
                                         .build()
            ),
            ImmutableList.of(),
            ImmutableList.of(),
            ImmutableList.of(),
            "1"));

    DiscoveryServer discoveryServer = new DiscoveryServer(cache);

    ServerBuilder builder = NettyServerBuilder.forAddress(new InetSocketAddress("0.0.0.0", 6000))
        //.addService(discoveryServer.getAggregatedDiscoveryServiceImpl())
        //.addService(discoveryServer.getClusterDiscoveryServiceImpl())
        .addService(discoveryServer.getEndpointDiscoveryServiceImpl())
        //.addService(discoveryServer.getListenerDiscoveryServiceImpl())
        //.addService(discoveryServer.getRouteDiscoveryServiceImpl())
    ;

    Server server = builder.build();

    server.start();

    System.out.println("Server has started on port " + server.getPort());

    Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));

    int i = 1;
    while(true) {
      i++;
      Thread.sleep(10000);
      // cache　を更新すると、envoy がeDS経由で取得しにくる
      System.out.println("Update version:" + i);
      cache.setSnapshot(
              GROUP,
              Snapshot.create(

                      ImmutableList.of(),
                      ImmutableList.of(
                              ClusterLoadAssignment.newBuilder()
                                                   .setClusterName("backend1")
                                                   .addEndpoints(
                                                           LocalityLbEndpoints.newBuilder().addLbEndpoints(
                                                                   LbEndpoint.newBuilder().setEndpoint(endpoint("127.0.0.1", 2000+i))))
                                                   .build(),
                              ClusterLoadAssignment.newBuilder()
                                                   .setClusterName("backend2")
                                                   .addEndpoints(
                                                           LocalityLbEndpoints.newBuilder().addLbEndpoints(
                                                                   LbEndpoint.newBuilder().setEndpoint(endpoint("127.0.0.1", 2000+i))))
                                                   .build()
                      ),
                      ImmutableList.of(),
                      ImmutableList.of(),
                      ImmutableList.of(),
                      "" + i));

    }

    //server.awaitTermination();
  }
}