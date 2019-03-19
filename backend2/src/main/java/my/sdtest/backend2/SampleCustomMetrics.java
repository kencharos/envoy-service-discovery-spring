package my.sdtest.backend2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;

@Component
public class SampleCustomMetrics {

    @Autowired
    MeterRegistry metrics;

    public void countCall() {
        metrics.counter("custom.backend2.callservice").increment();
    }

}
