package org.openapitools.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.Flight;
import org.openapitools.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public Optional<Flight> findById(int id){
        return flightRepository.findById(id);
    }

    public List<Flight> findAll(){
        return flightRepository.findAll();
    }
}
