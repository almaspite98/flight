package org.openapitools.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.openapitools.model.Flight;
import org.openapitools.model.Route;
import org.openapitools.model.RouteFinder;
import org.openapitools.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public Flight create(Flight flight) {
        log.debug("Flight create(Flight flight): {}", flight.toString());
        return flightRepository.save(flight);
    }

    public void update(Flight flight) { flightRepository.save(flight); }

    public Optional<Flight> findById(String id) {
        log.debug("Optional<Flight> findById(int id): {}", id);
        return flightRepository.findById(id);
    }

    //TODO
    public List<Flight> findAll(String from, String to, Instant departure, String airline) {
        log.debug("List<Flight> findAll()");
        if (to != null)
            return flightRepository.findAllByFromCityAndToCityAndDepartureTimeGreaterThan(from, to, departure);
        else
            return flightRepository.findAllByFromCityAndDepartureTimeGreaterThan(from, departure);
    }

    //TODO
    public Optional<Flight> findBy(String from, String to, Instant departure, String airline) {
        log.debug("List<Flight> findAll()");
        return flightRepository.findOne(FlightRepository.hasAirline(airline));
    }

    public List<Route> routes(String from, String to, Instant departure, Integer maxWait, String airline) {
        //15 talalat vagy 5 melyseg
        var routeFinder = new RouteFinder(this, from, to, departure, airline, maxWait);
        return routeFinder.resolve();
    }

//    public List<Route> routes(String from, String to, Instant departure, Integer maxWait, String airline) {
//        //15 talalat vagy 5 melyseg
//        var startingFlights = findAll(from, to, departure.truncatedTo(ChronoUnit.DAYS), null, airline);
//        if (startingFlights.size() >= 15) {
//            return startingFlights.stream()
//                    .map(flight -> Route.builder().flights(List.of(flight)).build())
//                    .collect(Collectors.toList());
//        }
//        //not to ?
//        var flights0 = findAll(from, null, departure.truncatedTo(ChronoUnit.DAYS), null, airline);
//        ArrayList<Route> routes = new ArrayList<>();
//        for(var flight : flights0){
//            if(flight.getToCity().equals(to))
//                continue;
//            var flights1 = findAll(flight.getToCity(), to, departure.truncatedTo(ChronoUnit.DAYS), maxWait, airline);
//            for(var flight1:flights1){
//                if( Duration.between(flight1.getDepartureTime(),flight.getArrivalTime()).toMinutes()<=maxWait){
//                    routes.add(Route.builder().flights(List.of(flight,flight1)).build());
//                }
//            }
//        }
//        return null;
//    }
//    public List<Route> routes(){
//        if(troutes.size()>15 || i>5)
//            return routes;
//        else
//            return routes();
//    }

    public void delete(String flightId) {
        flightRepository.deleteById(flightId);
    }
}
