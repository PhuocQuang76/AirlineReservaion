package com.synergisticit.service;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Flight;
import com.synergisticit.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    FlightRepository flightRepository;
    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findAllWithSort(String field) {
        return flightRepository.findAll(Sort.by(field));
    }

    @Override
    public Flight findById(Long flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if(flight.isPresent()){
            return flight.get();
        }else{
            return null;
        }
    }

    @Override
    public Flight findByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight update(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteById(Long flightId) {
        flightRepository.deleteById(flightId);
    }

    @Override
    public List<Flight> searchFlights(String departtureCity, String arrivalCity, LocalDate startDate, LocalDate endDate) {
        return flightRepository.findByFlightDepartureCityAndFlightArrivalCityAndFlightDepartureDateBetween(departtureCity,arrivalCity,startDate,endDate);
    }
}
