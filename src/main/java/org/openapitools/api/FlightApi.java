package org.openapitools.api;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.openapitools.model.Airline;
import org.openapitools.model.Flight;
import org.openapitools.model.Route;
import org.openapitools.service.AirlineService;
import org.openapitools.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/flight")
public class FlightApi {
    private final FlightService flightService;
    private final AirlineService airlineService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Flight created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal server error occured")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Flight create(
            @ApiParam(value = "Api key", required = true) @RequestHeader(value = "api_key", required = true) String api_key,
            @Valid @RequestBody Flight flight) {

        Airline airline = airlineService.findByApi_key(api_key);
        // Authenticate and authorise
        if (airline == null || !airline.getName().equals(flight.getAirline())){

        }
        return flightService.create(flight);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Flight not found"),
            @ApiResponse(code = 500, message = "Internal server error occured")})
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{flightId}")
    public void deleteFlight(
            @PathVariable("flightId") int flightId,
            @ApiParam(value = "Api key", required = true) @RequestHeader(value = "api_key", required = true) String api_key) {

        Optional<Flight> flight = flightService.findById(flightId);
        if (!flight.isPresent()){

        }

        Airline airline = airlineService.findByApi_key(api_key);
        // Authenticate and authorise
        if (airline == null || !airline.getName().equals(flight.get().getAirline())){

        }
        flightService.delete(flightId);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping("/routes")
    public List<Route> routes(@RequestParam(value = "from", required = false) String from,
                              @RequestParam(value = "to", required = false) String to,
                              @RequestParam(value = "departure", required = false) Instant departure,
                              @RequestParam(value = "maxWait", required = false) Integer maxWait,
                              @RequestParam(value = "airline", required = false) String airline) {
        return flightService.routes(from, to, departure, maxWait, airline);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping
    public List<Flight> findAll(@RequestParam(value = "from", required = false) String from,
                              @RequestParam(value = "to", required = false) String to,
                              @RequestParam(value = "departure", required = false) Instant departure,
                              @RequestParam(value = "maxWait", required = false) Integer maxWait,
                              @RequestParam(value = "airline", required = false) String airline) {
        return flightService.findAll(from, to, departure, airline);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Flight.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Flight not found"),
            @ApiResponse(code = 500, message = "Internal server error occured")})
    @GetMapping("/{flightId}")
    public Flight getFlightById(
            @ApiParam(value = "ID of flight to return", required = true) @PathVariable("flightId") Integer flightId) {
        // check if flight exists
        Optional<Flight> flight = flightService.findById(flightId);
        if (!flight.isPresent()){

        }
        return flightService.findById(flightId).get();
    }


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Flight updated"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Flight not found"),
            @ApiResponse(code = 405, message = "Validation exception"),
            @ApiResponse(code = 500, message = "Internal server error occured")})
    @PutMapping
    public ResponseEntity<Void> updateFlight(
            @ApiParam(value = "Flight object that needs to be added to the database", required = true) @Valid @RequestBody Flight newFlight,
            @ApiParam(value = "Api key", required = true) @RequestHeader(value = "api_key", required = true) String api_key) {
        Optional<Flight> flightExist = flightService.findById(newFlight.getFlightId());
        if (!flightExist.isPresent()){

        }
        Flight flight = flightExist.get();

        Airline airline = airlineService.findByApi_key(api_key);
        // Authenticate and authorise
        if (airline == null || !airline.getName().equals(flight.getAirline())){

        }
        flight.setArrivalTime(newFlight.getArrivalTime());
        flight.setDepartureTime(newFlight.getDepartureTime());
        flight.setFromCity(newFlight.getFromCity());
        flight.setToCity(newFlight.getToCity());
        flight.setNumberOfSeats(newFlight.getNumberOfSeats());
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Reservation is now Pending"),
            @ApiResponse(code = 401, message = "Not logged in"),
            @ApiResponse(code = 403, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal server error occured")})
    @PostMapping("/reserve")
    public ResponseEntity<Void> reserve(@ApiParam(value = "Security token", required = true) @RequestHeader(value = "token", required = true) String token, @ApiParam(value = "Flight IDs of flights to reserve", required = true) @Valid @RequestBody Object reservation) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
