package com.stacksimplify.restservices.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.stacksimplify.restservices.entities.View;
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
@JacksonXmlRootElement(localName = "user")
public class UserMmDTO extends RepresentationModel {
	@Size(min=2, message="Firstname should have at least 2 characters")
	private String firstname;
	@NotEmpty(message = "Username is Mandatory field. Please provide username")
	@JsonView(View.External.class)
	private String username;
	@JsonView(View.External.class)
	private String lastname;
	@JsonView(View.External.class)
	private String email;
	//@JsonIgnore
	@JsonView(View.Internal.class)
	private String role;
	@JsonView(View.Internal.class)
	private String ssn;
	@JsonView(View.Internal.class)
	private String address;

}

