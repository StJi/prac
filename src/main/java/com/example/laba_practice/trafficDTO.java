package com.example.laba_practice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.datatype.XMLGregorianCalendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class trafficDTO {
    @JsonProperty("traffic")
    private int traffic;
    @JsonProperty("street")
    private String street;
    @JsonProperty("date")
    private XMLGregorianCalendar date;
}