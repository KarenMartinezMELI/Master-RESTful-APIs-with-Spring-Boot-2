package com.stacksimplify.restservices.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDetails {

	private String firstname;
	private String username;
	private String lastname;
	private String email;
	
	
	//Fields Constructor
	public UserDetails(String firstname, String lastname, String email, String username) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
	}


	
}
