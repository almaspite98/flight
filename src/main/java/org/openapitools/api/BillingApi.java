package org.openapitools.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.openapitools.model.Payment;
import org.openapitools.model.PaymentResult;
import org.openapitools.service.PaymentService;
import org.openapitools.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/billing")
public class BillingApi {
    private final ReservationService reservationService;
    private final PaymentService paymentService;

    @Operation(summary = "External payment provider telling if payment was successful")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment registered"),
            @ApiResponse(responseCode = "401", description = "Unauthorised"),
            @ApiResponse(responseCode = "404", description = "Reservation does not exist") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<PaymentResult> billing(@RequestBody @Valid PaymentResult result) {
        Payment payment = paymentService.findById(result.getReservation_id());
        if (payment==null){

        }
        payment.setStatus(result.getStatus());
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
