package com.synergisticit.repository;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByUserIdAndFlightId(Long userId, Long flightId);
    List<Passenger> findByReservation_ReservationNumber(Long resevationId);
}
