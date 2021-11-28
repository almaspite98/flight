package org.openapitools.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class Route implements Cloneable {
//    String fromCity;
//    String toCity;
//    Instant departureDay;
//    int maxWait;
    List<Flight> flights = new ArrayList<Flight>();

    public Route(Route newRoute, Flight newFlight) {
        this(new ArrayList<Flight>(newRoute.getFlights()));
        flights.add(newFlight);
    }

}

