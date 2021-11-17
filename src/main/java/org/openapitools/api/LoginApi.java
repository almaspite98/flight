/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.openapitools.model.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-11-15T21:12:04.614312100+01:00[Europe/Prague]")
@Validated
@Api(value = "login", description = "the login API")
public interface LoginApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /login : Login a user
     *
     * @param user Username and hash of password (required)
     * @return Logged in. Use token supplied. (status code 201)
     *         or Incorrect username or password (status code 401)
     */
    @ApiOperation(value = "Login a user", nickname = "login", notes = "", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Logged in. Use token supplied."),
        @ApiResponse(code = 401, message = "Incorrect username or password") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/login",
        consumes = { "application/json", "application/xml" }
    )
    default ResponseEntity<Void> login(@ApiParam(value = "Username and hash of password", required = true) @Valid @RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
