package com.kcs.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public void testMapping()
	{
		System.out.println("test mapping !!");
	}
}
