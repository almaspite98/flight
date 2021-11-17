/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

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
@Api(value = "routes", description = "the routes API")
public interface RoutesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
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
    @ApiOperation(value = "List routes", nickname = "routes", notes = "", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/routes"
    )
    default ResponseEntity<Void> routes(@NotNull @ApiParam(value = "Filter", required = true) @Valid @RequestParam(value = "from", required = true) String from,@NotNull @ApiParam(value = "Filter", required = true) @Valid @RequestParam(value = "to", required = true) String to,@NotNull @ApiParam(value = "Filter", required = true) @Valid @RequestParam(value = "departure", required = true) String departure,@ApiParam(value = "Filter") @Valid @RequestParam(value = "maxWait", required = false) Integer maxWait,@ApiParam(value = "Filter") @Valid @RequestParam(value = "airline", required = false) String airline) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
