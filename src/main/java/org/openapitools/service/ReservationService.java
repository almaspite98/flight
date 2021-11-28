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

    public void update(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> findAllByGroupId(Integer groupId){
        return reservationRepository.findAllByGroupId(groupId);
    };
}
