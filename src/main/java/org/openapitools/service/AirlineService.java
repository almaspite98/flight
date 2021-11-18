package org.openapitools.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.Airline;
import org.openapitools.repository.AirlineRepository;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class AirlineService {
    private final AirlineRepository airlineRepository;

    public Airline findByApiKey(String api_key){
        return airlineRepository.findByApiKey(api_key);
    }
}
