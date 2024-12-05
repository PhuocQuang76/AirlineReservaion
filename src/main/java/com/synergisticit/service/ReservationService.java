package com.synergisticit.service;


import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    public List<Reservation> findAll();

    Reservation findByReservationNumber(Long reservationNumber);

    public Reservation save(Reservation reservation);

    public Reservation update(Reservation reservation);

    public void deleteById(Long reservationId);

    Reservation updateBagAndCheckIn(Long reservationNumber, int NumOfBags);

    List<Reservation> findByFlightId(Long flightId);
}
