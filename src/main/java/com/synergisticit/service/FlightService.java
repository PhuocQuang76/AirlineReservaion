package com.synergisticit.service;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface FlightService {
    public List<Flight> findAll();
    public List<Flight> findAllWithSort(String field);

    Flight findById(Long flightId);

    Flight findByFlightNumber(String flightNumber);

    public Flight save(Flight flight);

    public Flight update(Flight flight);

    public void deleteById(Long flightId);

    public List<Flight> searchFlights(String departtureCity, String arrivalCity, LocalDate startDate, LocalDate endDate);
}
