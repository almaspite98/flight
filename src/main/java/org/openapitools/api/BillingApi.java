package org.openapitools.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.Payment;
import org.openapitools.model.Reservation;
import org.openapitools.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/billing")
public class BillingApi {
    private final ReservationService reservationService;

    @Operation(summary = "External payment provider telling if payment was successful")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment registered"),
            @ApiResponse(responseCode = "401", description = "Unauthorised"),
            @ApiResponse(responseCode = "404", description = "Reservation does not exist")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Payment billing(@RequestBody @Valid Payment payment) {
        List<Reservation> reservations = reservationService.findAllByGroupId(payment.getReservation_id());
        if (reservations.isEmpty())
            throw new NoSuchElementException("Reservation doesnt exist");
        if (!reservations.get(0).getStatus().equals("PENDING"))
            throw new IllegalStateException("Reservation is in wrong state");

        log.info("Setting status to " + payment.getStatus());
        // Set status. Revert functionality is done by another app
        for (Reservation r : reservations) {
            r.setStatus(payment.getStatus());
            reservationService.update(r);
        }
        return payment;
    }

}
