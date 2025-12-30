package com.fickri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;

@Configuration
public class ZipkinTracingConfig {
    @Bean
    public ZipkinSpanExporter zipkinSpanExporter() {
        return ZipkinSpanExporter.builder()
                .setEndpoint("http://zipkin:9411/api/v2/spans")
                .build();
    }
}
