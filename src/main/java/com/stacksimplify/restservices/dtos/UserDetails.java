package com.stacksimplify.restservices.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDetails {
	@Size(min=2, message="Firstname should have at least 2 characters")
	private String firstname;
	@NotEmpty(message = "Username is Mandatory field. Please provide username")
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
