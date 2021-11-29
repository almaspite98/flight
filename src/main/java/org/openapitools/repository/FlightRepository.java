package org.openapitools.repository;

import org.openapitools.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String>, JpaSpecificationExecutor<Flight> {

    List<Flight> findAllByAirline(String airline);

    List<Flight> findAllByFromCityAndToCityAndDepartureTimeGreaterThan(String from, String to, Instant fromTime);

    List<Flight> findAllByFromCityAndToCityAndDepartureTimeGreaterThanAndAirline(String from, String to, Instant fromTime, String airLine);

    List<Flight> findAllByFromCityAndDepartureTimeGreaterThan(String from, Instant fromTime);

    List<Flight> findAllByFromCityAndDepartureTimeGreaterThanAndAirline(String from, Instant fromTime, String airLine);


    List<Flight> findAllByFromCityAndToCityAndDepartureTimeBetween(String from, String to, Instant fromTime, Instant toTime);

    List<Flight> findAllByFromCityAndToCityAndDepartureTimeBetweenAndAirline(String from, String to, Instant fromTime, Instant toTime, String airLine);

    List<Flight> findAllByFromCityAndDepartureTimeBetween(String from, Instant fromTime, Instant toTime);

    List<Flight> findAllByFromCityAndDepartureTimeBetweenAndAirline(String from, Instant fromTime, Instant toTime, String airLine);
}
