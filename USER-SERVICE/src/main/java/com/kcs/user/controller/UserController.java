package com.kcs.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcs.user.core.User;
import com.kcs.user.dto.JwtRequest;
import com.kcs.user.dto.JwtResponse;
import com.kcs.user.dto.ResponseVO;
import com.kcs.user.dto.UserResponseDto;
import com.kcs.user.dto.UserVO;
import com.kcs.user.service.UserService;
import com.kcs.user.utils.JwtUtil;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	//@Autowired
	//private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public  ResponseVO<?> addEditUser(@RequestBody UserVO uservo)
	{
		return userService.addEditUser(uservo); 
	}
	
	@DeleteMapping("/delete")
	public ResponseVO<?> deleteById(@RequestParam ("id") Long id)
	{
		return userService.deleteUser(id);
		
	}
	
	@GetMapping("/listUser")
	public List<UserResponseDto> listUser()
	{
		return userService.findAllUser();
		
	}
	
	@GetMapping("/getUser")
	public ResponseVO<?> getUser(@RequestParam ("id") Long id)
	{
		return userService.getUserById(id);
		
	}
	

	
	@PostMapping("/login")
	public ResponseVO<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		System.out.println("service called..");
		return userService.generateToken(authenticationRequest);
		
	}

	/*
	 * @PostMapping("/register") public ResponseVO<?> registerUser(@RequestBody
	 * UserVO uservo) { // Persist user to some persistent storage
	 * System.out.println("Info saved..."); return userService.addEditUser(uservo);
	 * //return new ResponseEntity<String>("Registered", HttpStatus.OK); }
	 */
}
