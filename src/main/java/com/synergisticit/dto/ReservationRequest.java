package com.synergisticit.dto;

import com.synergisticit.domain.BookStatus;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationRequest {	@GeneratedValue(strategy = GenerationType.IDENTITY)

    @OneToMany(mappedBy = "reservation")
    private List<Passenger> passengers;

//    @ManyToOne
//    @JoinColumn(name = "flight_Id")
//    private Flight flight;

    private Long flightId;

    private int checkedBags;

    private BookStatus status;

}