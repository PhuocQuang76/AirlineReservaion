package com.synergisticit.repository;

import com.synergisticit.domain.Flight;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findByFlightNumber(String flightNumber);

    List<Flight> findByFlightDepartureCityAndFlightArrivalCityAndFlightDepartureDateBetween(String departureCity, String arrivalCity, LocalDate from, LocalDate to);

    Page<Flight> findAll(Pageable pageable);

}
