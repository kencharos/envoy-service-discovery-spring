package my.sdtest.front;

import java.util.concurrent.atomic.AtomicReference;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ChannelManager {

    private final String host;
    private final int port;
    private final AtomicReference<ManagedChannel> ref = new AtomicReference<>();

    public ChannelManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ManagedChannel get() {

        if(ref.get() == null) {
            ref.set(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build());
        }
        return ref.get();
    }
}
