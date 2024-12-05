package com.synergisticit.service;

import com.synergisticit.domain.Reservation;
import com.synergisticit.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    ReservationRepository reservationRepository;


    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findByReservationNumber(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            return reservation.get();
        }else{
            return null;
        }
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Reservation updateBagAndCheckIn(Long reservationNumber, int NumOfBags) {
        Reservation reservation = findByReservationNumber(reservationNumber);
        reservation.setCheckedBags(NumOfBags);


        return  reservationRepository.save(reservation);

    }

    @Override
    public List<Reservation> findByFlightId(Long flightId) {
        return reservationRepository.findByFlightId(flightId);
    }
}
