package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="")
public class GreetingController {

	@GetMapping(value="/{user}")
	public String getGreetings(@PathVariable("user") String user) {
		return "Hello " +  user;
	}
}
