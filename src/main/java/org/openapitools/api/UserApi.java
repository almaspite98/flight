package org.openapitools.api;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.openapitools.model.Preferences;
import org.openapitools.model.User;
import org.openapitools.model.UserWithPreferences;
import org.openapitools.service.UserWithPreferenceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
