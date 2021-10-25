package com.stacksimplify.restservices.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {

	private String firstname;
	private String lastname;
	private String city;
	
	
	//Fields Constructor
	public UserDetails(String firstname, String lastname, String city) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
	}
	
	//To String
	@Override
	public String toString() {
		return "UserDetails [firstname=" + firstname + ", lastname=" + lastname + ", city=" + city + "]";
	}
	
	
	
}
