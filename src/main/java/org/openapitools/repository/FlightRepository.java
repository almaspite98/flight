package org.openapitools.repository;

import org.openapitools.model.Flight;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>, JpaSpecificationExecutor<Flight> {

//    List<Flight> findAll(hasAirline())

    List<Flight> findAllByFromCityAndToCityAndDepartureTimeGreaterThan( String from,String to, Instant time);
    List<Flight> findAllByFromCityAndDepartureTimeGreaterThan( String from, Instant time);

    static Specification<Flight> hasAirline(String airline) {
        if(airline != null)
            return (flight, cq, cb) -> cb.equal(flight.get("airline"), airline);
        else return Specification.where(null);
    }

//    static Specification<Flight> hasMaxWait(Integer maxWait) {
//        if(maxWait != null)
//            return (flight, cq, cb) -> cb.equal(flight.get("airline"), airline);
//        else return Specification.where(null);
//    }

    static Specification<Flight> titleContains(String title) {
        return (flight, cq, cb) -> cb.like(flight.get("title"), "%" + title + "%");
    }
}
