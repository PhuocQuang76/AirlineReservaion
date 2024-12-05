package com.synergisticit.repository;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long> {
    public Reservation findByReservationNumber(Long reservationNumber);

    public List<Reservation> findByFlightId(Long flightId);
}
