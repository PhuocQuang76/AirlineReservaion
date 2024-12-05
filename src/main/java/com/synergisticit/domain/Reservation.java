package com.synergisticit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Reservation
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationNumber; //ticket number

//	@JsonBackReference
//	@OneToOne
//	private Passenger passenger;


	@OneToMany(mappedBy = "reservation", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Passenger> passengers;

//	@OneToMany(mappedBy = "reservation")
//	private List<Passenger> passengers;
//	@ManyToMany(mappedBy="reservations")
//	private List<Passenger> passengers = new ArrayList<>();

	private Long flightId;

//	@ManyToOne
//	@JoinColumn(name = "flight_Id")
//	private Flight flight;
	
	private int checkedBags;

//	private boolean checkedIn;

	private BookStatus status;

}
