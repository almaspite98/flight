package org.openapitools.api;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.openapitools.model.Flight;
import org.openapitools.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/flight")
@CrossOrigin
public class FlightApi {
    private final FlightService flightService;

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
        return flightService.create(flight, api_key);
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
            @PathVariable("flightId") String flightId,
            @RequestHeader(value = "api_key", required = true) String api_key) {
        flightService.delete(flightId, api_key);
    }



    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping
    public List<Flight> findAll(@RequestHeader(value = "api_key", required = true) String api_key) {
        return flightService.findAllWithApiKey(api_key);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Flight.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Flight not found"),
            @ApiResponse(code = 500, message = "Internal server error occured")})
    @GetMapping("/{flightId}")
    public Flight getFlightById(@RequestHeader(value = "api_key", required = true) String api_key,
                                @PathVariable("flightId") String flightId) {
        return flightService.findById(flightId, api_key);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Flight updated"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Flight not found"),
            @ApiResponse(code = 405, message = "Validation exception"),
            @ApiResponse(code = 500, message = "Internal server error occured")})
    @PutMapping
    public Flight updateFlight(
            @Valid @RequestBody Flight newFlight,
            @RequestHeader(value = "api_key", required = true) String api_key) {
        return flightService.update(newFlight, api_key);
    }
}
