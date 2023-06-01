package com.kcs.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@GetMapping("/organizationServiceFallBack")
	public String organizationServiceFallback() {
		return "Organization Service is down!";
	}

	@GetMapping("/floorServiceFallBack")
	public String floorServiceFallback() {
		return "floor Service is down!";
	}

	@GetMapping("/officeServiceFallBack")
	public String officeServiceFallback() {
		return "office Service is down!";
	}

	@GetMapping("/deskServiceFallBack")
	public String deskServiceFallback() {
		return "desk Service is down!";
	}

	@GetMapping("/userServiceFallBack")
	public String userServiceFallback() {
		return "user Service is down!";
	}
}
