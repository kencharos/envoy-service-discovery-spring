##

## run sample envoy

```
sudo ifconfig lo0 alias 10.200.10.1/24
cd envoy-sample
docker build -t sample-envoy .
docker run -d -p 3000:3000 -p 10001:10001 sample-envoy
```