package com.synergisticit.service;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    FlightService flightService;
    @Override
    public List<Flight> search(Search search) {
        String departtureCity = search.getFlightDepartureCity();
        String arrivalCity = search.getFlightArrivalCity();
        LocalDate from = search.getFlightDepartureDateStart();
        LocalDate to = search.getFlightDepartureDateEnd();

        return flightService.searchFlights(departtureCity, arrivalCity, from, to);
    }
}
