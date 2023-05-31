package com.example.laba_practice;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class prod {
    public static prod instance;

    @Autowired
    ProducerTemplate myProd;

    @PostConstruct
    public void init()
    {
        instance = this;
    }
}
