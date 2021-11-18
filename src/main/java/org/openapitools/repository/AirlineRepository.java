package org.openapitools.repository;

import org.openapitools.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer>, JpaSpecificationExecutor<Airline> {
    Airline findByApiKey(String apiKey);
}