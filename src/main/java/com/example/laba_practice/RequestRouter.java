package com.example.laba_practice;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.UnmarshalException;


@Component
public class RequestRouter extends RouteBuilder {
    @Value("${kafka-requests-path}")
    private String from_path;

    @Override
    public void configure() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        JaxbDataFormat jaxb = new JaxbDataFormat(jaxbContext);

        onException(UnmarshalException.class)
                .handled(true)
                .setBody(simple("<status>error</status><message>failed</message>"))
                .to("direct:status")
                .to("direct:add_error")
                .to("direct:stop_timer");

        // Kafka Consumer
        from(from_path)
                .to("direct:add_mes")
                .to("direct:start_timer")
                .unmarshal(jaxb)
                .choice()
                .when(body().isInstanceOf(traffic.class))
                    .to("direct:save")
                .otherwise()
                .setBody(simple("<status>error</status><message>XML data error</message>"))
                .to("direct:status")
                .to("direct:add_error")
                .to("direct:stop_timer");

    }
}
