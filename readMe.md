# envoy java grpc sample

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
  + call beckend2  backend2-proxy(3002) -> backend2-app(6567)
## backend1

+ backend1-proxy
    + ingress 3001
    + egress 8001 (to backend2)
+ backend1-app
    + http 8081
    + grpc 6566


## backend2

+ backend2-proxy
    + ingress 3002
+ backend2-app
    + http 8082
    + grpc 6567

