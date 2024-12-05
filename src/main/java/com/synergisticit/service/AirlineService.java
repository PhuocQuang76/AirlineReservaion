package com.synergisticit.service;

import com.synergisticit.domain.Airline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirlineService {

    public List<Airline> findAll();

    Airline findById(Long airlineId);

    public Airline save(Airline airlines);

    public Airline update(Airline airline);

    public void deleteById(Long airlineId);
}
