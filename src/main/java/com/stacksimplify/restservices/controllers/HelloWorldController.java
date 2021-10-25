package com.stacksimplify.restservices.controllers;


import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.services.IUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	private final IUserService userService;

	HelloWorldController(IUserService userService){
		this.userService=userService;
	}
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hello World1";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return userService.returnNewUserDetails();
	}
	
}
