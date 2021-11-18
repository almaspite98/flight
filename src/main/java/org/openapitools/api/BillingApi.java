package org.openapitools.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.openapitools.model.Payment;
import org.openapitools.model.PaymentResult;
import org.openapitools.model.PaymentStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/billing")
public class BillingApi {


    /**
     * POST /billing : Calling external payment provider
     *
     * @body payment Result of the payment. Either SUCCESSFUL or FAILED (required)
     * @return Payment registered (status code 201)
     *         or Unauthorised (status code 401)
     *         or Reservation does not exist (status code 404)
     */
    @Operation(summary = "Calling external payment provider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment registered"),
            @ApiResponse(responseCode = "401", description = "Unauthorised"),
            @ApiResponse(responseCode = "404", description = "Reservation does not exist") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<PaymentResult> billing(@RequestBody @Valid Payment payment) {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
