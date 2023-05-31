package com.example.laba_practice;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StatusRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:status")
                .to("kafka:status_topic?brokers=localhost:9092");
    }
}
