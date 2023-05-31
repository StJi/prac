package com.example.laba_practice;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest(properties = {"kafka-requests-path=direct:requests"})

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints
public class LabaPracticeApplicationTests {

    @Autowired
    ProducerTemplate prod;

    @EndpointInject("mock:jpa:com.example.laba_practice.traffic")
    public MockEndpoint saveToDb;

    @EndpointInject("mock:kafka:results")
    public MockEndpoint kafkaResults;

    @EndpointInject("mock:kafka:status_topic")
    public MockEndpoint kafkaStatusTopic;

    @Test
    public void saveTest() throws InterruptedException {
        com.example.laba_practice.traffic traffic = new com.example.laba_practice.traffic();
        traffic.setTraffic(3);
        traffic.setStreet("Gagarina");
        saveToDb.expectedBodiesReceived(traffic);

        prod.sendBody("direct:requests", "<traffic><traffic>3</traffic>" +
                "<street>Gagarina</street><date>2023-08-22</date></traffic>");

        MockEndpoint.assertIsSatisfied(saveToDb);
    }

    @Test
    public void resTest() throws InterruptedException {
        kafkaResults.expectedBodiesReceived("{\"traffic\":3}");
        prod.sendBody("direct:requests", "<traffic><traffic>3</traffic>" +
                "<street>Gagarina</street><date>2023-08-22</date></traffic>");

        MockEndpoint.assertIsSatisfied(kafkaResults);
    }

}
