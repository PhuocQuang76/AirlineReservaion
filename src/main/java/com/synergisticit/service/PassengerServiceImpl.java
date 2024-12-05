package com.synergisticit.service;

import com.synergisticit.domain.Passenger;
import com.synergisticit.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService{
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(Long passengerId) {
        Optional<Passenger> passenger = passengerRepository.findById(passengerId);
        if(passenger.isPresent()){
            return passenger.get();
        }else{
            return null;
        }
    }

    @Override
    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger update(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public void deleteById(Long passengerId) {
        passengerRepository.deleteById(passengerId);
    }

    @Override
    public List<Passenger> findByUserIdAndFlightId(Long userId, Long flightId) {
        return passengerRepository.findByUserIdAndFlightId(userId,flightId);
    }

    @Override
    public List<Passenger> findByReservation_ReservationNumbe(Long reservationId) {
        return passengerRepository.findByReservation_ReservationNumber(reservationId);
    }
}
