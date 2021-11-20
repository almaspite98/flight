package org.openapitools.repository;

import org.openapitools.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>, JpaSpecificationExecutor<Reservation> {
    List<Reservation> findAllByGroupId(Integer groupId);
}
