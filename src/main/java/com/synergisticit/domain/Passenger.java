package com.synergisticit.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Passenger
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passengerId;

	private Long flightId;

	private Long userId;

	private String passengerFirstName;
	
	private String passengerLastName;
	
	private String passengerEmail;
	
	private String passengerPhoneNo;
	
	private Gender passengerGender;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate passengerDOB;
	
	@Embedded
	private Address passengerAddress;


//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(name="reservation_passenger",
//			joinColumns = { @JoinColumn(name="reservationId")},
//			inverseJoinColumns = {@JoinColumn(name="passengerId")})
//	List<Reservation> reservations = new ArrayList<>();

//	@ManyToOne
//	@JoinColumn(name = "reservation_Id")
//	private Reservation reservation;

	@ManyToOne
	private Reservation reservation;

	@Override
	public String toString() {
		return "Passenger{" +
				"passengerId=" + passengerId +
				", flightId=" + flightId +
				", userId=" + userId +
				", passengerFirstName='" + passengerFirstName + '\'' +
				", passengerLastName='" + passengerLastName + '\'' +
				", passengerEmail='" + passengerEmail + '\'' +
				", passengerPhoneNo='" + passengerPhoneNo + '\'' +
				", passengerGender=" + passengerGender +
				", passengerDOB=" + passengerDOB +
				", passengerAddress=" + passengerAddress +
				'}';
	}
}
