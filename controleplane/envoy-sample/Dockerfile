FROM envoyproxy/envoy:latest
COPY ./sample-envoy.yaml /etc/envoy/envoy.yaml
CMD /usr/local/bin/envoy -c /etc/envoy/envoy.yaml --service-node sample-node --service-cluster sample-proxy
