package my.sdtest.front;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.cloud.sleuth.instrument.grpc.SpringAwareManagedChannelBuilder;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ChannelManager {

    private final String host;
    private final int port;
    private final AtomicReference<ManagedChannel> ref = new AtomicReference<>();
    private final SpringAwareManagedChannelBuilder builder;

    public ChannelManager(String host, int port, SpringAwareManagedChannelBuilder builder) {
        this.host = host;
        this.port = port;
        this.builder = builder;
    }

    public ManagedChannel get() {

        if(ref.get() == null) {
            ref.set(builder.forAddress(host, port).usePlaintext().build());
        }
        return ref.get();
    }
}
