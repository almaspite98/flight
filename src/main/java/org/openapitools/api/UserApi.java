package org.openapitools.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.openapitools.model.Preferences;
import org.openapitools.model.User;
import org.openapitools.model.UserWithPreferences;
import org.openapitools.service.PaymentService;
import org.openapitools.service.ReservationService;
import org.openapitools.service.UserWithPreferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.Optional;
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserApi{
    private final UserWithPreferenceService userService;

    @ApiOperation(value = "Get preferences", nickname = "getpreferences", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised") })
    @GetMapping
    public Preferences getPreferences(
            @ApiParam(value = "Security token", required = true) @RequestHeader(value = "token", required = true) String token) {
        UserWithPreferences user = userService.findByToken(token);
        // if invalid token
        if (user == null) {

        }
        return new Preferences(user.getDeparture(), user.getTransferTime(), user.getAirline());
    }

    @ApiOperation(value = "Set preferences", nickname = "setpreferences", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Updated."),
            @ApiResponse(code = 401, message = "Unauthorised") })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/user",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> setpreferences(
            @ApiParam(value = "Security token", required = true) @RequestHeader(value = "token", required = true) String token,
            @ApiParam(value = "Preferences", required = true) @Valid @RequestBody Preferences preferences) {
        UserWithPreferences user = userService.findByToken(token);

        // if invalid token
        if (user == null) {

        }
        user.setAirline(preferences.getAirline());
        user.setDeparture(preferences.getDeparture());
        user.setTransferTime(preferences.getTransferTime());
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @ApiOperation(value = "Login a user", nickname = "login", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Logged in. Use token supplied."),
            @ApiResponse(code = 401, message = "Incorrect username or password") })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> login(
            @ApiParam(value = "Username and hash of password", required = true) @Valid @RequestBody User user) {
        UserWithPreferences userwithpreferences = userService.findByEmail(user.getEmail());

        // if password is correct
        if (userwithpreferences.getPassword().equals(user.getPassword())){
            // generate token
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            String token = bytes.toString();
            userwithpreferences.setToken(token);
        }
        else {

        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @ApiOperation(value = "Register a user", nickname = "register", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registered."),
            @ApiResponse(code = 403, message = "Already registered") })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/register",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> register(
            @ApiParam(value = "Username and hash of password", required = true) @Valid @RequestBody User user) {
        UserWithPreferences userwithpreferences = userService.findByEmail(user.getEmail());

        // if user already exists
        if (userwithpreferences != null){

        }
        userService.create(user);

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
