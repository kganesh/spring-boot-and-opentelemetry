package com.example.hello;

import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.metrics.MeterProvider;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This component is instrumented using the OpenTelemetry API, not Micrometer.
 */
@Component
class SomeOpenTelemetryApiInstrumentedComponent {

    private final LongCounter counter;

    SomeOpenTelemetryApiInstrumentedComponent(MeterProvider meterProvider) {
        Meter meter = meterProvider.get(getClass().getName());
        this.counter = meter.counterBuilder("dummy-counter").build();
    }

    @Scheduled(fixedRateString = "1s")
    private void run() {
        this.counter.add(1);
    }
}
