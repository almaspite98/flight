package org.openapitools.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.Flight;
import org.openapitools.model.Reservation;
import org.openapitools.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public void reserveRoute(Integer groupId, List<Flight> flights, String email){
        for (Flight i : flights){
            create(groupId, i.getFlightId(), email);
        }
    }
    public Reservation create(Integer groupId, Integer flightId, String email) {
        log.debug("Reservation create(Integer groupId, Integer flightId): {} {}", groupId, flightId);
        Reservation reservation = new Reservation();
        reservation.setGroupId(groupId);
        reservation.setFlightId(flightId);
        reservation.setEmail(email);
        return reservationRepository.save(reservation);
    }
    public boolean groupIdInUse(Integer groupId){
        if (reservationRepository.findByGroupId(groupId)==null)
            return false;
        return true;
    }
}
