package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Airline
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long airlineId;
	
	private String airlineName;
	
	private String airlineCode;
	//	@JsonBackReference
@JsonIgnore
	@OneToMany(mappedBy = "flightAirline")
	private List<Flight> airlineFlights = new ArrayList<>();


}
