package org.openapitools.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.service.FlightService;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

@Slf4j
@Data
public class RouteFinder {
    FlightService service;

    String sourceCity;
    String targetCity;

    Instant departure;
    String airline;

    Integer maxWaitTime;

    static final int maxDepth = 10;
    static final int maxResultSize = 20;
    ArrayList<Route> result = new ArrayList<>();
    ArrayList<Route> pendingVisits = new ArrayList<>();


    public RouteFinder(FlightService service, String sourceCity, String targetCity, Instant departure, String airline, Integer maxWaitTime) {
        this.service = service;
        this.sourceCity = sourceCity;
        this.targetCity = targetCity;
        this.departure = departure;
        this.airline = airline;
        this.maxWaitTime = maxWaitTime;
    }

    public ArrayList<Route> resolve() {
        var emptyRoute = new Route();
        pendingVisits.add(emptyRoute);

        int depth = 0;
        while (result.size() < maxResultSize && depth++ < maxDepth)
            passDepth();

        return result;
    }

    private void passDepth() {
        var currentPendingVisits = pendingVisits;
        pendingVisits = new ArrayList<>();
        for (var entry : currentPendingVisits) {
            visit(entry);
            if (result.size() >= maxResultSize)
                return;
        }
    }


    private void visit(Route previousRoute) {
        final boolean first_city = previousRoute.flights.isEmpty();

        var lastFlight = first_city ? (Flight) null : previousRoute.flights.get(previousRoute.flights.size() - 1);
        var lastCity = first_city ? sourceCity : lastFlight.getToCity();
        var lastArrival = first_city ? departure : lastFlight.getArrivalTime();
        var children = service.findAll(lastCity, null, lastArrival, airline);

        for (var child : children) {
            if (maxWaitTime == null || lastFlight == null || Duration.between(lastFlight.getArrivalTime(), child.getDepartureTime()).toMinutes() <= maxWaitTime) {
                var currentRoute = new Route(previousRoute, child);
                if (child.getToCity().equals(targetCity)) {
                    result.add(currentRoute);
                    if (result.size() >= maxResultSize)
                        return;
                } else {
                    pendingVisits.add(new Route(previousRoute, child));
                }
            }
        }
    }
}
