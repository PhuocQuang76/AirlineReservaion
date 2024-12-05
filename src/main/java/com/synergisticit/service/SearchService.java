package com.synergisticit.service;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Search;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    List<Flight> search(Search search);
}
