package org.openapitools.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.Payment;
import org.openapitools.model.PaymentStatus;
import org.openapitools.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@AllArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment create(Integer reservationId){
        Payment payment = new Payment();
        payment.setStatus("PENDING");
        payment.setReservationId(reservationId);
        payment.setTimestamp(Instant.now());
        log.debug("Payment create(Payment payment): {}", payment.toString());
        return paymentRepository.save(payment);
    }

    public Payment findById(Integer groupId){
        return paymentRepository.getPaymentByReservationId(groupId);
    }
}
