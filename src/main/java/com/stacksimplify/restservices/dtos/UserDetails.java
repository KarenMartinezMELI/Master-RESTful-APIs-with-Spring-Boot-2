package com.stacksimplify.restservices.dtos;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserDetails extends RepresentationModel {
	@Size(min=2, message="Firstname should have at least 2 characters")
	private String firstname;
	@NotEmpty(message = "Username is Mandatory field. Please provide username")
	private String username;
	private String lastname;
	private String email;
}
