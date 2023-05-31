package com.example.laba_practice;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Date;
import java.util.GregorianCalendar;

@Mapper(componentModel = "spring")
public interface mapper {
    @Mapping(target = "traffic", source = "traffic")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "date", source = "date")
    trafficDTO mapWithoutId(com.example.laba_practice.traffic traffic);

    @Mapping(target = "traffic", source = "traffic")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "date", source = "date")
    com.example.laba_practice.traffic mapGenerated(com.example.laba_practice.otraffic otraffic);

    default Date map(XMLGregorianCalendar calendar)
    {
        return new java.sql.Date(calendar.toGregorianCalendar().getTime().getTime());
    }

    default String mapXMLGregorianCalendar(XMLGregorianCalendar date) {
        return date.toString();
    }

    default XMLGregorianCalendar mapDate(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        DatatypeFactory dTF = null;
        try {
            dTF = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        return dTF.newXMLGregorianCalendar(gregorianCalendar);
    }
}