package com.synergisticit.domain;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
public class Address
{
	private String addressLine1;
	
	private String addressLine2;
	
	private String city;
	
	private String State;
	
	private String Country;
	
	private String zipcode;

	public Address() {}

	public Address(String addressLine1, String addressLine2, String city, String state, String country, String zipcode) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		State = state;
		Country = country;
		this.zipcode = zipcode;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
