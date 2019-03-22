# envoy java grpc sample

- [x] static client side load balancing with grpc 
- [x] dynamic load balancing and service discovery with xDS control plane.
- [x] ~~distribute tracing with envoy~~ (envoy tracing cannot header propagation, so that use seleuth at each services)
- [x] distribute tracing with spring seleuth
- [x] prometheus, grafana, micrometer custom metrics
- [x] integration with consul KVStore and spring cloud config
- [x] prometheus service discovery from consul
  - [ ] envoy cds, join dynamic cluster config.
- [x] integration with hashicorp vault

## consul, vault

NOTE. consul run local. I doesn't know to run consul in docker...

`consul agent --config-dir consul --ui --dev --client=0.0.0.0`

`vault server --config consul-vault/vault-conf.hcl`
set up vault ref to [consul-vault/readme.md](./consul-vault/readme.md)

(optional) set vault kv

```
vault kv put secret/controleplane sensitive.description=secretdescription
vault kv put secret/application sensitive.user=secretuser sensitive.pass=secretpath

```


## controlPlane

envoy xDS control server

ports

+ 8084: web api
+ 6000: gRPC port for envoy proxies.

### usage

at running, endpoint is empty.

docker alias name does not set in EDS, so that use host network. 

if using Mac, put following command, then replace 127.0.0.1 to 10.200.10.1 .

POST http://localhost:8084/sidecar for sidecar register to consul

```
sudo ifconfig lo0 alias 10.200.10.1/24
```
set consul key value store of `key envoy/eds` these json.
( or POST http://localhost:8084/endpoint these json.)
TODO:  contorleplane cache load first this value on running..
```
    {
      "group":"sample_cluster",
      "clusters":[
        {
          "clusterName":"backend1",
          "endpoints":[
              {"address":"127.0.0.1", "port":3001}
            ]
        },
        {
          "clusterName":"backend2",
          "endpoints":[
              {"address":"127.0.0.1", "port":3002}
            ]
        }
      ]
    }
```

to loadbalancing backend2

```
    {
      "group":"sample_cluster",
      "clusters":[
        {
          "clusterName":"backend1",
          "endpoints":[
              {"address":"127.0.0.1", "port":3001}
            ]
        },
        {
          "clusterName":"backend2",
          "endpoints":[
              {"address":"127.0.0.1", "port":3002},
              {"address":"127.0.0.1", "port":3003}
            ]
        }
      ]
    }
```

## front

+ front-proxy
    + ingress : 3000
    + egress(to backend) : 8000
    + admin 10001
+ front-app
    + port:8080

### route

+ external -> front-proxy(3000) -> front-app(8080) ->
  + call backend1  backend1-proxy(3001) -> backend1-app(6566) -> backend1-proxy(egress 8001) -> backend2-proxy(3002) -> backend2-app(6567)    
                                                                                             -> backend2-proxy-b(3003) -> backend2-app-b(6568) 
  + call beckend2  backend2-proxy(3002) -> backend2-app(6567)  
                   backend2-proxy-b(3003) -> backend2-app-b(6568) 
## backend1

+ backend1-proxy
    + ingress 3001
    + egress 8001 (to backend2)
    + admin 10002
+ backend1-app
    + http 8081
    + grpc 6566


## backend2

+ backend2-proxy
    + ingress 3002
    + admin 10003
+ backend2-app
    + http 8082
    + grpc 6567

## backend2-b

scaleuped backend2.
backend2-cluster in envoy loadbalances　backend2-proxy and backend2-proxy-b

+ backend2-proxy-b
    + ingress 3003
    + admin 10004
+ backend2-app-b
    + http 8083
    + grpc 6568


# monitoring

`docker-compose  -d -f docker-compose-monitoring.yaml up`

+ zipkin 9411
+ prometheus 9090
+ grafana 3100

## grafana + prometheus

accsee localhost:3100, create datasource for prometheus.
set 127.0.0.1:9090, or 10.200.10.1:9090 (mac).
in dashbbord section, import prometheus dashboads.

