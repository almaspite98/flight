package org.openapitools.api;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.openapitools.model.Preferences;
import org.openapitools.model.Route;
import org.openapitools.model.User;
import org.openapitools.model.UserWithPreferences;
import org.openapitools.service.FlightService;
import org.openapitools.service.UserWithPreferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/user")
public class UserApi {
    private final UserWithPreferenceService userService;
    private final FlightService flightService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised")})
    @GetMapping
    public Preferences getPreferences(@RequestHeader(value = "token", required = true) String token) {
        return userService.getPreferences(token);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Updated."),
            @ApiResponse(code = 401, message = "Unauthorised")})
    @PostMapping
    public UserWithPreferences setPreferences(
            @RequestHeader(value = "token", required = true) String token,
            @Valid @RequestBody Preferences preferences) {
        return userService.setPreferences(preferences, token);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Logged in. Use token supplied."),
            @ApiResponse(code = 401, message = "Incorrect username or password")})
    @PostMapping("/login")
    public UserWithPreferences login(@Valid @RequestBody User user) {
        return userService.login(user);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registered."),
            @ApiResponse(code = 403, message = "Already registered")})
    @PostMapping("/register")
    public UserWithPreferences register(@Valid @RequestBody User user) {
        return userService.create(user);
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
            @ApiResponse(code = 201, message = "Reservation is now Pending"),
            @ApiResponse(code = 401, message = "Not logged in"),
            @ApiResponse(code = 403, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal server error occured")})
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/reserve")
    public String reserve(
            @RequestHeader(value = "token", required = true) String token,
            @Valid @RequestBody Route route) {
        return flightService.reserve(token, route);
    }
}
