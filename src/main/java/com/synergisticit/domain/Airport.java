package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

public class Airport
{
	@Id
	private Long airportId;
	
	private String airportCode;
	
	private String airportName;
	
	private String airportCity;


	@OneToMany
	private List<Flight> airportArrivalFlights = new ArrayList<>();


	@OneToMany
	private List<Flight> airportDepartureFlights = new ArrayList<>();

	public Airport(){}

	public Airport(Long airportId, String airportCode, String airportName, String airportCity, List<Flight> airportArrivalFlights, List<Flight> airportDepartureFlights) {
		this.airportId = airportId;
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.airportCity = airportCity;
		this.airportArrivalFlights = airportArrivalFlights;
		this.airportDepartureFlights = airportDepartureFlights;
	}

	public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCity() {
		return airportCity;
	}

	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}

	public List<Flight> getAirportArrivalFlights() {
		return airportArrivalFlights;
	}

	public void setAirportArrivalFlights(List<Flight> airportArrivalFlights) {
		this.airportArrivalFlights = airportArrivalFlights;
	}

	public List<Flight> getAirportDepartureFlights() {
		return airportDepartureFlights;
	}

	public void setAirportDepartureFlights(List<Flight> airportDepartureFlights) {
		this.airportDepartureFlights = airportDepartureFlights;
	}
}
