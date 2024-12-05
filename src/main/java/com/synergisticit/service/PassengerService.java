package com.synergisticit.service;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengerService {
    public List<Passenger> findAll();

    Passenger findById(Long passengerId);

    public Passenger save(Passenger passenger);

    public Passenger update(Passenger passenger);

    public void deleteById(Long passengerId);

    public List<Passenger> findByUserIdAndFlightId(Long userId,Long flightId);

    public List<Passenger> findByReservation_ReservationNumbe(Long reservationId);
}

