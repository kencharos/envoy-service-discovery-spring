FROM consul
COPY ./consul.config.json /consul/config/consul.config.json
EXPOSE 8500
EXPOSE 8600
# docker run -p 8500:8500 -p 8600:8600 -e CONSUL_CLIENT_INTERFACE='eth0' -e CONSUL_BIND_INTERFACE='eth0'
CMD ["consul", "agent", "-config-dir", "/consul/config", "--dev", "--ui", "--client=0.0.0.0"]