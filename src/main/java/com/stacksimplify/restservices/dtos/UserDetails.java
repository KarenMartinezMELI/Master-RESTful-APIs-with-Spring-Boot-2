package com.stacksimplify.restservices.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {

	private String firstname;
	private String lastname;
	private String email;
	
	
	//Fields Constructor
	public UserDetails(String firstname, String lastname, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	//To String
	@Override
	public String toString() {
		return "UserDetails [firstname=" + firstname + ", lastname=" + lastname + ", city=" + email + "]";
	}
	
	
	
}
