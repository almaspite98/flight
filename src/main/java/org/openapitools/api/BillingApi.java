package org.openapitools.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.openapitools.model.Flight;
import org.openapitools.model.PaymentResult;
import org.openapitools.model.Reservation;
import org.openapitools.service.FlightService;
import org.openapitools.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/billing")
public class BillingApi {
    private final ReservationService reservationService;
    private final FlightService flightService;

    @Operation(summary = "External payment provider telling if payment was successful")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment registered"),
            @ApiResponse(responseCode = "401", description = "Unauthorised"),
            @ApiResponse(responseCode = "404", description = "Reservation does not exist") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<PaymentResult> billing(@RequestBody @Valid PaymentResult result) {
        List<Reservation> reservations = reservationService.findAllByGroupId(result.getReservation_id());
        // reservation does not exist or not in pending state
        if (reservations.isEmpty() || !reservations.get(0).getStatus().equals("PENDING")){

        }

        // Set status. Revert functionality is done by another app
        for (Reservation r : reservations) {
            r.setStatus(result.getStatus());
            reservationService.update(r);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
