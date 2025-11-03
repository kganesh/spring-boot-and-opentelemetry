package com.example.hello;

import java.time.Duration;

import io.opentelemetry.api.metrics.MeterProvider;
import io.opentelemetry.exporter.otlp.http.metrics.OtlpHttpMetricExporter;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.MetricExporter;
import io.opentelemetry.sdk.metrics.export.MetricReader;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration is needed so that OpenTelemetry API instrumented components can publish metrics.
 */
@Configuration(proxyBeanMethods = false)
class OpenTelemetryMetricsConfiguration {
    @Bean
    MeterProvider meterProvider(Resource resource, MetricReader metricReader) {
        return SdkMeterProvider.builder()
            .registerMetricReader(metricReader)
            .setResource(resource).build();
    }

    @Bean
    PeriodicMetricReader metricReader(MetricExporter exporter, @Value("${management.otlp.metrics.export.step:1m}") Duration interval) {
        return PeriodicMetricReader.builder(exporter).setInterval(interval).build();
    }

    @Bean
    OtlpHttpMetricExporter metricExporter(@Value("${management.otlp.metrics.export.url}") String url) {
        return OtlpHttpMetricExporter.builder().setEndpoint(url).build();
    }
}
