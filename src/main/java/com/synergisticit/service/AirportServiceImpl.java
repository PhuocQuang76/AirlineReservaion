package com.synergisticit.service;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.repository.AirlineRepository;
import com.synergisticit.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService{
    @Autowired
    AirportRepository airportRepository;

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport findById(Long airportId) {
        Optional<Airport> airport = null;
        airport =  airportRepository.findById(airportId);
        if(airport.isPresent()){
            return airport.get();
        }
        return null;
    }

    @Override
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport update(Airport airport) {

        return airportRepository.save(airport);
    }

    @Override
    public void deleteById(Long airportId) {
        airportRepository.deleteById(airportId);
    }
}
