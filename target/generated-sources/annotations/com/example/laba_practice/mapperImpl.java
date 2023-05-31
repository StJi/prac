package com.example.laba_practice;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-31T18:17:45+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class mapperImpl implements mapper {

    @Override
    public trafficDTO mapWithoutId(traffic traffic) {
        if ( traffic == null ) {
            return null;
        }

        trafficDTO trafficDTO = new trafficDTO();

        if ( traffic.getTraffic() != null ) {
            trafficDTO.setTraffic( traffic.getTraffic() );
        }
        trafficDTO.setStreet( traffic.getStreet() );
        trafficDTO.setDate( mapDate( traffic.getDate() ) );

        return trafficDTO;
    }

    @Override
    public traffic mapGenerated(otraffic otraffic) {
        if ( otraffic == null ) {
            return null;
        }

        traffic traffic = new traffic();

        traffic.setTraffic( otraffic.getTraffic() );
        traffic.setStreet( otraffic.getStreet() );
        traffic.setDate( map( otraffic.getDate() ) );

        return traffic;
    }
}
