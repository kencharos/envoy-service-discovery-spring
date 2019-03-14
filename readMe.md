# envoy java grpc sample

## front

+ front-proxy
    + ingress : 3000
    + egress(to backend) : 8000
    + admin 10001
+ front-app
    + port:8080

### route

external -> front-proxy(3000) -> front-app(8080) call backend grpc -> front-proxy(8000) -> backend1(6567) or backend2(6568) 

## backend1

+ backend1-app
    + http 8081
    + grpc 6567


## backend2

+ backend2-app
    + http 8082
    + grpc 6568

