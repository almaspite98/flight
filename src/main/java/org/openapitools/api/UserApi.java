package org.openapitools.api;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.openapitools.model.Preferences;
import org.openapitools.model.User;
import org.openapitools.model.UserWithPreferences;
import org.openapitools.service.UserWithPreferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.SecureRandom;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/user")
public class UserApi {
    private final UserWithPreferenceService userService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised")})
    @GetMapping
    public Preferences getPreferences(
            @ApiParam(value = "Security token", required = true) @RequestHeader(value = "token", required = true) String token) {
        UserWithPreferences user = userService.findByToken(token);
        // if invalid token
        if (user == null) {

        }
        return new Preferences(user.getDeparture(), user.getTransferTime(), user.getAirline());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Updated."),
            @ApiResponse(code = 401, message = "Unauthorised")})
    @PostMapping
    public ResponseEntity<Void> setPreferences(
            @ApiParam(value = "Security token", required = true) @RequestHeader(value = "token", required = true) String token,
            @ApiParam(value = "Preferences", required = true) @Valid @RequestBody Preferences preferences) {
        System.out.println("Token: " + token);
        UserWithPreferences user = userService.findByToken(token);

        // if invalid token
        if (user == null) {
            System.out.println("Invalid token");

        }
        System.out.println("User is " + user.getEmail());

        user.setAirline(preferences.getAirline());
        user.setDeparture(preferences.getDeparture());
        user.setTransferTime(preferences.getTransferTime());
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

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
}
