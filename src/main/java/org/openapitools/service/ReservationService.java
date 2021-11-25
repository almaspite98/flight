package org.openapitools.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.Reservation;
import org.openapitools.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation create(Integer groupId, String flightId, String email) {
        log.debug("Reservation create(Integer groupId, Integer flightId): {} {}", groupId, flightId);
        Reservation reservation = new Reservation();
        reservation.setGroupId(groupId);
        reservation.setFlightId(flightId);
        reservation.setEmail(email);
        reservation.setTimestamp(Instant.now());
        reservation.setStatus("PENDING");
        return reservationRepository.save(reservation);
    }

    public void update(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> findAllByGroupId(Integer groupId){
        return reservationRepository.findAllByGroupId(groupId);
    };

    public boolean groupIdInUse(Integer groupId){
        if (reservationRepository.findAllByGroupId(groupId).isEmpty())
            return false;
        return true;
    }
}
