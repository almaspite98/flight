package org.openapitools.api;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.openapitools.model.Flight;
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
    /**
     * POST /flight : Add a new flight to the database
     *
     * @param body Flight object that needs to be added to the database (required)
     * @return Flight created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Invalid input (status code 405)
     *         or Internal server error occured (status code 500)
     */
//    @ApiOperation(value = "Add a new flight to the database", nickname = "addFlight", notes = "", authorizations = {

//            @Authorization(value = "api_key")
//    }, tags={ "flight", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Flight created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal server error occured") })
    @PostMapping
    public ResponseEntity<Void> addFlight(@ApiParam(value = "Flight object that needs to be added to the database", required = true) @Valid @RequestBody Flight body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /flight/{flightId} : Deletes a flight
     *
     * @param flightId Flight id to delete (required)
     * @param apiKey  (optional)
     * @return Deleted (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Unauthorized (status code 401)
     *         or Flight not found (status code 404)
     *         or Internal server error occured (status code 500)
     */
//    @ApiOperation(value = "Deletes a flight", nickname = "deleteFlight", notes = "", authorizations = {

//            @Authorization(value = "api_key")
//    }, tags={ "flight", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Flight not found"),
            @ApiResponse(code = 500, message = "Internal server error occured") })
    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@ApiParam(value = "Flight id to delete", required = true) @PathVariable("flightId") int flightId, @ApiParam(value = "") @RequestHeader(value = "api_key", required = false) String apiKey) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /routes : List routes
     *
     * @param from Filter (required)
     * @param to Filter (required)
     * @param departure Filter (required)
     * @param maxWait Filter (optional)
     * @param airline Filter (optional)
     * @return OK (status code 200)
     */
////    @ApiOperation(value = "List routes", nickname = "routes", notes = "", tags={ "user", })

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK") })
    @GetMapping("/routes")
    public List<Flight> routes(@NotNull @ApiParam(value = "Filter", required = false) @Valid @RequestParam(value = "from", required = false) String from, @NotNull @ApiParam(value = "Filter", required = false) @Valid @RequestParam(value = "to", required = false) String to, @NotNull @ApiParam(value = "Filter", required = false) @Valid @RequestParam(value = "departure", required = false) String departure, @ApiParam(value = "Filter") @Valid @RequestParam(value = "maxWait", required = false) Integer maxWait, @ApiParam(value = "Filter") @Valid @RequestParam(value = "airline", required = false) String airline) {
        return flightService.findAll();
    }

    /**
     * GET /flight/{flightId} : Find flight by ID
     * Returns a single flight
     *
     * @param flightId ID of flight to return (required)
     * @return OK (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Unauthorized (status code 401)
     *         or Flight not found (status code 404)
     *         or Internal server error occured (status code 500)
     */
//    @ApiOperation(value = "Find flight by ID", nickname = "getFlightById", notes = "Returns a single flight", response = Flight.class, authorizations = {

//            @Authorization(value = "api_key")
//    }, tags={ "flight", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Flight.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Flight not found"),
            @ApiResponse(code = 500, message = "Internal server error occured") })
    @GetMapping("/{flightId}")
    public Optional<Flight> getFlightById(@ApiParam(value = "ID of flight to return", required = true) @PathVariable("flightId") Integer flightId) {
//        getRequest().ifPresent(request -> {
//            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
//                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
//                    String exampleString = "{ \"arrival_time\" : \"2000-01-23T04:56:07.000+00:00\", \"from_city\" : \"from_city\", \"to_city\" : \"to_city\", \"id\" : 0, \"departure_time\" : \"2000-01-23T04:56:07.000+00:00\", \"number_of_seats\" : 6 }";
//                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
//                    break;
//                }
////                if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
////                    String exampleString = "<Flight> <id>123456789</id> <departure_time>2000-01-23T04:56:07.000Z</departure_time> <arrival_time>2000-01-23T04:56:07.000Z</arrival_time> <from_city>aeiou</from_city> <to_city>aeiou</to_city> <number_of_seats>123456789</number_of_seats> </Flight>";
////                    ApiUtil.setExampleResponse(request, exampleString);
////                    break;
////                }
//            }
//        });
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//        return Flight.builder().id(12).departureTime(Instant.now().plus(Duration.ofHours(11))).arrivalTime(Instant.now().plus(Duration.ofHours(12))).fromCity("Budapest").toCity("Kairo").build();
        return flightService.findById(flightId);
    }


    /**
     * PUT /flight : Update an existing flight
     *
     * @param body Flight object that needs to be added to the database (required)
     * @return Flight updated (status code 201)
     *         or Invalid ID supplied (status code 400)
     *         or Unauthorized (status code 401)
     *         or Flight not found (status code 404)
     *         or Validation exception (status code 405)
     *         or Internal server error occured (status code 500)
     */
//    @ApiOperation(value = "Update an existing flight", nickname = "updateFlight", notes = "", authorizations = {

//            @Authorization(value = "api_key")
//    }, tags={ "flight" })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Flight updated"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Flight not found"),
            @ApiResponse(code = 405, message = "Validation exception"),
            @ApiResponse(code = 500, message = "Internal server error occured") })
    @PutMapping
    public ResponseEntity<Void> updateFlight(@ApiParam(value = "Flight object that needs to be added to the database", required = true) @Valid @RequestBody Flight body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /reserve : Make a reservation
     *
     * @param token Security token (required)
     * @param reservation Flight IDs of flights to reserve (required)
     * @return Reservation is now Pending (status code 201)
     *         or Not logged in (status code 401)
     *         or Invalid input (status code 403)
     *         or Internal server error occured (status code 500)
     */
//    @ApiOperation(value = "Make a reservation", nickname = "reserve", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Reservation is now Pending"),
            @ApiResponse(code = 401, message = "Not logged in"),
            @ApiResponse(code = 403, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal server error occured") })
    @PostMapping("/reserve")
    public ResponseEntity<Void> reserve(@ApiParam(value = "Security token", required = true) @RequestHeader(value = "token", required = true) String token,@ApiParam(value = "Flight IDs of flights to reserve", required = true) @Valid @RequestBody Object reservation) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
