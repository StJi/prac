package com.example.laba_practice;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MetricsRouter extends RouteBuilder {

    private long startTime = 0;
    private String messageBody;
    @Override
    public void configure() throws Exception {
        from("direct:add_mes")
                .to("sql:UPDATE messages SET send = send + 1;");
        from("direct:add_error")
                .to("sql:UPDATE messages SET error = error + 1;");
        from("direct:add_good")
                .to("sql:UPDATE messages SET good = good + 1;");
        from("direct:start_timer")
                .process(exchange -> {
                    startTime = System.currentTimeMillis();
                    messageBody = exchange.getIn().getBody(String.class);
                });
        from("direct:stop_timer")
                .process(exchange -> {
                    exchange.setProperty("message", messageBody);
                    exchange.setProperty("time", System.currentTimeMillis() - startTime);
                })
                .to("sql:INSERT INTO messages (message, time) VALUES(:#${exchangeProperty.message}, :#${exchangeProperty.time});");
    }
}
