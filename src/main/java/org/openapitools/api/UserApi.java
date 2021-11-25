package org.openapitools.api;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(
                            mediaType = "application/html"
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/admin")
    public String getAdminPage() {
//        userService.getAdminPage();
        return "<html>\n" + "<header><title>Welcome</title></header>\n" +
                "<body>\n" + "Hello world\n" + "</body>\n" + "</html>";
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorised")})
    @GetMapping
    public Preferences getPreferences(@RequestHeader(value = "token", required = true) String token) {
        return userService.getPreferences(token);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated."),
            @ApiResponse(responseCode = "401", description = "Unauthorised")})
    @PostMapping
    public UserWithPreferences setPreferences(
            @RequestHeader(value = "token", required = true) String token,
            @Valid @RequestBody Preferences preferences) {
        return userService.setPreferences(preferences, token);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Logged in. Use token supplied."),
            @ApiResponse(responseCode = "401", description = "Incorrect username or password")})
    @PostMapping("/login")
    public UserWithPreferences login(@Valid @RequestBody User user) {
        return userService.login(user);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registered."),
            @ApiResponse(responseCode = "403", description = "Already registered")})
    @PostMapping("/register")
    public UserWithPreferences register(@Valid @RequestBody User user) {
        return userService.create(user);
    }
}
