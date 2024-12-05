package com.synergisticit.service;

import com.synergisticit.controller.AirlineController;
import com.synergisticit.domain.Airline;
import com.synergisticit.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService{

    @Autowired
    AirlineRepository airlineRepository;
    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline findById(Long airlineId) {
        Optional<Airline> airline = airlineRepository.findById(airlineId);
        if(airline.isPresent()){
            return airline.get();
        }else{
            return null;
        }
    }

    @Override
    public Airline save(Airline airlines) {
        return airlineRepository.save(airlines);
    }

    @Override
    public Airline update(Airline airline) {
        System.out.println("Updating  airline ....");
        return airlineRepository.save(airline);
    }

    @Override
    public void deleteById(Long airlineId) {
        airlineRepository.deleteById(airlineId);
    }
}
