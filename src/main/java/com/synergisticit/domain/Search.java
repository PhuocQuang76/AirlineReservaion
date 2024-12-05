package com.synergisticit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long searchId;

    private String flightDepartureCity;

    private String flightArrivalCity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate flightDepartureDateStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate flightDepartureDateEnd;

    public String getFlightDepartureCity() {
        return flightDepartureCity;
    }

    public void setFlightDepartureCity(String flightDepartureCity) {
        this.flightDepartureCity = flightDepartureCity;
    }

    public String getFlightArrivalCity() {
        return flightArrivalCity;
    }

    public void setFlightArrivalCity(String flightArrivalCity) {
        this.flightArrivalCity = flightArrivalCity;
    }

    public LocalDate getFlightDepartureDateStart() {
        return flightDepartureDateStart;
    }

    public void setFlightDepartureDateStart(LocalDate flightDepartureDateStart) {
        this.flightDepartureDateStart = flightDepartureDateStart;
    }

    public LocalDate getFlightDepartureDateEnd() {
        return flightDepartureDateEnd;
    }

    public void setFlightDepartureDateEnd(LocalDate flightDepartureDateEnd) {
        this.flightDepartureDateEnd = flightDepartureDateEnd;
    }
}
