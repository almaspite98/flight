package org.openapitools.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.openapitools.model.Airline;
import org.openapitools.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Slf4j
@AllArgsConstructor
@Service
public class AirlineService {
    private final AirlineRepository airlineRepository;

    @SneakyThrows
    public Airline findByApiKey(String api_key, String airLine){
        var airline =  airlineRepository.findByApiKey(api_key);
        // Authenticate and authorise
        if (airline == null || !airline.getName().equals(airLine)) {
            throw new AuthenticationException("Invalid apikey or airline");
        }
        return airline;
    }

    @SneakyThrows
    public Airline findByApiKey(String api_key){
        var airline =  airlineRepository.findByApiKey(api_key);
        // Authenticate and authorise
        if (airline == null) {
            throw new AuthenticationException("Invalid apikey or airline");
        }
        return airline;
    }
}
