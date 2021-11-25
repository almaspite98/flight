package org.openapitools.service;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.openapitools.model.*;
import org.openapitools.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirlineService airlineService;
    private final UserWithPreferenceService userService;
    private final ReservationService reservationService;


    @SneakyThrows
    public Flight create(Flight flight, String apiKey) {
        log.debug("Flight create(Flight flight): {}", flight.toString());
        airlineService.findByApiKey(apiKey, flight.getAirline());
        return flightRepository.save(flight);
    }

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight update(Flight newFlight, String api_key) {
        Flight oldFlight = findById(newFlight.getFlightId());

        airlineService.findByApiKey(api_key, newFlight.getAirline());

        newFlight.setFlightId(oldFlight.getFlightId());
        return save(newFlight);
    }

    @SneakyThrows
    public Flight findById(String id) {
        log.debug("Optional<Flight> findById(int id): {}", id);
        // check if flight exists
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight == null) {
            throw new NoSuchElementException("Flight not found by id: " + id);
        }
        return flight;
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

    @SneakyThrows
    public String reserve(String token, Route route) {
        UserWithPreferences user = userService.findByToken(token);
        for (Flight f : route.getFlights()) {
            Flight flight = findById(f.getFlightId());
            if (flight.getNumberOfSeats() <= 0) {
                // no seats left
                throw new IllegalArgumentException("There is no seat left on flight " + flight.getFlightId());
            }
        }

        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3366/javabase","root","root");
        Statement stmt=con.createStatement();
        stmt.execute("start transaction");
        stmt.execute("SET @newID := (SELECT MAX(group_id) FROM reservations)+1");
        stmt.execute("SET @time := (SELECT NOW())");
        String sql = "SET @fail := (SELECT COUNT(*) FROM FLIGHTS\nWHERE ";
        for (Flight i : route.getFlights()) {
            sql += "(flight_id=\""+i.getFlightId()+"\" AND number_of_seats < 1) OR ";
        }
        sql += "0=1);\n";
        stmt.execute(sql);
        for (Flight i : route.getFlights()) {
            String insert = "INSERT INTO reservations (email, flight_id, group_id, status, timestamp)\n" +
                    "VALUES ('" + user.getEmail() + "', '" + i.getFlightId() + "', @newID, IF(@fail>0, 'CANCELLED', 'PENDING'), @time);\n";
            stmt.execute(insert);
            String update = "UPDATE flights\nSET number_of_seats = IF(@fail>0, number_of_seats, number_of_seats-1)\n" +
                    "WHERE flight_id='" + i.getFlightId() + "';";
            stmt.execute(update);
        }
        stmt.execute("commit");
        /*String sql = "START TRANSACTION;\nSET @newID := (SELECT MAX(group_id) FROM reservations)+1;\nSET @time := (SELECT NOW());\n";
        sql += "SET @fail := (SELECT COUNT(*) FROM FLIGHTS\n" + "WHERE ";
        for (Flight i : route.getFlights()) {
            sql += "(flight_id=\""+i.getFlightId()+"\" AND number_of_seats < 1) OR ";
        }
        sql += "0=1);\n";
        for (Flight i : route.getFlights()) {
            String insert = "INSERT INTO reservations (email, flight_id, group_id, status, timestamp)\n" +
                    "VALUES ('" + user.getEmail() + "', '" + i.getFlightId() + "', @newID, IF(@fail>0, 'CANCELLED', 'PENDING'), @time);\n";
            sql += insert;
            String update = "UPDATE flights\nSET number_of_seats = IF(@fail>0, number_of_seats, number_of_seats-1)\n" +
                    "WHERE flight_id='" + i.getFlightId() + "';";
            sql += update;
        }
        sql += "\nCOMMIT;\n";
        System.out.println(sql);
        */
        //stmt.execute(sql);
/*
        SecureRandom random = new SecureRandom();
        Integer reservationId = random.nextInt();
        // WARRNING: THIS ALL SHOULD BE A TRANSACTION
        // get a valid unique reservationId
        while (reservationService.groupIdInUse(reservationId)) {
            reservationId = random.nextInt();
        }

        for (Flight i : route.getFlights()) {
            reservationService.create(reservationId, i.getFlightId(), user.getEmail());
            Flight f = findById(i.getFlightId());
            f.setNumberOfSeats(f.getNumberOfSeats() - 1);
            save(f);
        }
        // END OF TRANSACTION
*/
        // external payment provider would be called here
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        return "OK";
    }

    public void delete(String flightId, String api_key) {
        Flight flight = findById(flightId);
        airlineService.findByApiKey(api_key, flight.getAirline());
        flightRepository.deleteById(flightId);
    }
}
