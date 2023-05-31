package com.example.laba_practice;

import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveRouter extends RouteBuilder {
    private final mapper mapper;

    public void configure() {
        from("direct:save")
            .choice()
            .when(body().isInstanceOf(otraffic.class))
                .process(exchange -> {
                    otraffic in = exchange.getIn().getBody(otraffic.class);
                    traffic traffic = mapper.mapGenerated(in);

                    exchange.getMessage().setBody(traffic, com.example.laba_practice.traffic.class);
                })
                .to("jpa:com.example.laba_practice.traffic")
                .process(exchange -> {
                    traffic in = exchange.getIn().getBody(traffic.class);
                    trafficDTO trafficD = mapper.mapWithoutId(in);

                    exchange.getMessage().setBody(trafficD, trafficDTO.class);
                })
                .marshal().json(JsonLibrary.Jackson)
                .to("kafka:results?brokers=localhost:9092")
                .setBody(simple("<status>ok</status>"))
                .to("direct:status")
                .to("direct:add_mes")
                .to("direct:stop_timer")
            .otherwise()
                .setBody(simple("<status>error</status><message>data error</message>"))
                .to("direct:status")
                .to("direct:add_error")
                .to("direct:stop_timer");
    }
}
