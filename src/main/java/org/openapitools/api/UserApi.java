package org.openapitools.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.openapitools.model.Preferences;
import org.openapitools.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserApi{

    /**
     * GET /user : Get preferences
     *
     * @param token Security token (required)
     * @param preferences  (required)
     * @return OK (status code 200)
     *         or Unauthorised (status code 401)
     */
//    @ApiOperation(value = "Get preferences", nickname = "getpreferences", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised") })
    @GetMapping
    public ResponseEntity<Void> getPreferences(@ApiParam(value = "Security token", required = true) @RequestHeader(value = "token", required = true) String token, @ApiParam(value = "", required = true) @RequestHeader(value = "preferences", required = true) Preferences preferences) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /user : Set preferences
     *
     * @param token Security token (required)
     * @param preferences Preferences (required)
     * @return Updated. (status code 201)
     *         or Unauthorised (status code 401)
     */
//    @ApiOperation(value = "Set preferences", nickname = "setpreferences", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Updated."),
            @ApiResponse(code = 401, message = "Unauthorised") })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/user",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> setpreferences(@ApiParam(value = "Security token", required = true) @RequestHeader(value = "token", required = true) String token,@ApiParam(value = "Preferences", required = true) @Valid @RequestBody Preferences preferences) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /login : Login a user
     *
     * @param user Username and hash of password (required)
     * @return Logged in. Use token supplied. (status code 201)
     *         or Incorrect username or password (status code 401)
     */
//    @ApiOperation(value = "Login a user", nickname = "login", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Logged in. Use token supplied."),
            @ApiResponse(code = 401, message = "Incorrect username or password") })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> login(@ApiParam(value = "Username and hash of password", required = true) @Valid @RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /register : Register a user
     *
     * @param user Username and hash of password (required)
     * @return Registered. (status code 201)
     *         or Already registered (status code 403)
     */
//    @ApiOperation(value = "Register a user", nickname = "register", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registered."),
            @ApiResponse(code = 403, message = "Already registered") })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/register",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> register(@ApiParam(value = "Username and hash of password", required = true) @Valid @RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
