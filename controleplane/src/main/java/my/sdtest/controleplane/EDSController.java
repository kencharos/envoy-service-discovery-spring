package my.sdtest.controleplane;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.agent.model.NewService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.envoyproxy.controlplane.cache.SimpleCache;
import io.envoyproxy.controlplane.cache.Snapshot;
import io.envoyproxy.envoy.api.v2.ClusterLoadAssignment;
import io.envoyproxy.envoy.api.v2.core.Address;
import io.envoyproxy.envoy.api.v2.core.SocketAddress;
import io.envoyproxy.envoy.api.v2.endpoint.Endpoint;
import io.envoyproxy.envoy.api.v2.endpoint.LbEndpoint;
import io.envoyproxy.envoy.api.v2.endpoint.LocalityLbEndpoints;

@RestController
public class EDSController {

    @Autowired
    private SimpleCache<String> cache;

    /*
    POST
    {
      "group":"cluster.local",
      "clusters":[
        {
          "clusterName":"backend1",
          "endpoints":[
              {"address":"127.0.0.1", "port":8011}
            ]
        },
        {
          "clusterName":"backend2",
          "endpoints":[
              {"address":"127.0.0.1", "port":8021}
            ]
        }
      ]
    }
     */
    @PostMapping("/endpoint")
    public void updateEndpoints(@RequestBody EndpointConfig newEndPoints) {

        cache.setSnapshot(newEndPoints.group,
                          Snapshot.create(List.of(),
                                          newEndPoints.clusters.stream().map(this::make).collect(Collectors.toList()),
                                          List.of(), List.of(), List.of(),
                                          System.currentTimeMillis()+""));

    }

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/endpoint/consul")
    public void updateEndpointsFromConsul(@RequestBody JsonNode kv) throws IOException {

        String base64Value = kv.get("Value").asText();

        updateEndpoints(mapper.readValue(Base64.getDecoder().decode(base64Value.getBytes()), EndpointConfig.class));

    }


    @Autowired
    ConsulClient consul;

    /**
     * sidecar の envoy は 各nodeにある consul agent が envoy admin にアクセスするなどして、情報を取得するか、
     * service登録ずみの app の内容から 類推して登録する。tagの情報を使用して登録を行うようにする。
     */
    @PostMapping("/sidecar") void refreshSidecar() {
        registerEnvoy();

    }

    @PostConstruct
    void registerEnvoy() {
        System.out.println("register side car to consul ");

        // サービスについて、 sidecar後段のアプリは、 tagに type=app, envoy=adminポート を持つ。
        // sidecar は、　サービス名 + "proxy" を持つルールにする。

        var services = consul.getCatalogServices(QueryParams.DEFAULT).getValue();
        var apps = services.entrySet().stream().filter(e -> e.getValue().contains("type=app"))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        var proxies = services.keySet().stream().filter(s -> s.endsWith("-proxy")).map(s -> s.substring(0, s.lastIndexOf("-")));

        proxies.forEach(n -> apps.remove(n));

        apps.forEach((name, tags) -> {
            consul.getCatalogService(name, QueryParams.DEFAULT).getValue().forEach(sv->{
                System.out.println("register " + name + "-proxy");

                var s = new NewService();
                s.setId(sv.getId()+"-proxy");
                s.setName(sv.getServiceName()+"-proxy");
                s.setEnableTagOverride(true);
                s.setAddress(sv.getAddress());
                s.setPort(sv.getServiceTags().stream().filter(t -> t.startsWith("envoy="))
                            .mapToInt(t-> Integer.parseInt(t.split("=")[1])).sum());
                s.setTags(List.of("type=proxy"));
                consul.agentServiceRegister(s);
            });
        });

    }



    private ClusterLoadAssignment make(ClusterConfig c) {

        return ClusterLoadAssignment.newBuilder()
                                    .setClusterName(c.clusterName)
                                    .addEndpoints(LocalityLbEndpoints.newBuilder().addAllLbEndpoints(make(c.endpoints)).build())
                                    .build();
    }

    private List<LbEndpoint> make(List<EPConfig> ep) {
        return ep.stream().map(e ->
         LbEndpoint.newBuilder()
              .setEndpoint(Endpoint.newBuilder().setAddress(
                      Address.newBuilder().setSocketAddress(
                              SocketAddress.newBuilder().setAddress(e.address).setPortValue(e.port).build()).build()).build())

                   .build()
        ).collect(Collectors.toList());
    }


    public static class EndpointConfig {
        private String group;
        private List<ClusterConfig> clusters;

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public List<ClusterConfig> getClusters() {
            return clusters;
        }

        public void setClusters(List<ClusterConfig> clusters) {
            this.clusters = clusters;
        }
    }

    public static class ClusterConfig {
        private String clusterName;
        private List<EPConfig> endpoints;

        public String getClusterName() {
            return clusterName;
        }

        public void setClusterName(String clusterName) {
            this.clusterName = clusterName;
        }

        public List<EPConfig> getEndpoints() {
            return endpoints;
        }

        public void setEndpoints(List<EPConfig> endpoints) {
            this.endpoints = endpoints;
        }
    }

    public static class EPConfig {
        private String address;
        private int port;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

}
