package com.synergisticit.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightId;

	private String flightNumber;


	@ManyToOne
	private Airline flightAirline;

	private String flightDepartureCity;

	private String flightArrivalCity;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate flightDepartureDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime flightDepartureTime;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate flightArrivalDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime flightArrivalTime;

	private int flightCapacity;

	private double flightPrice;

	private int flightSeatsBooked;


//	@JsonIgnore
//	@OneToMany(mappedBy = "flight")
//	private List<Reservation> reservations;
}