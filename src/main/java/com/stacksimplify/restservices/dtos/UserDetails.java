package com.stacksimplify.restservices.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
//Ignore by list
//@JsonIgnoreProperties({"firstname, lastname"})
public class UserDetails extends RepresentationModel {
	@Size(min=2, message="Firstname should have at least 2 characters")
	private String firstname;
	@NotEmpty(message = "Username is Mandatory field. Please provide username")
	private String username;
	private String lastname;
	private String email;
	//@JsonIgnore
	private String ssn;


}
