package com.synergisticit.service;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportService {
    public List<Airport> findAll();

    Airport findById(Long airportId);

    public Airport save(Airport airport);

    public Airport update(Airport airport);

    public void deleteById(Long airportId);
}
