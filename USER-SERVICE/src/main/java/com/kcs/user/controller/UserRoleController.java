package com.kcs.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcs.user.dto.ResponseVO;
import com.kcs.user.dto.UserRoleVO;
import com.kcs.user.service.UserRoleService;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

	

	@Autowired
	private UserRoleService userRoleService;
	
	@GetMapping("/add")
	public  ResponseVO<?> addEditUser(@RequestBody UserRoleVO userRoleVO)
	{
		return userRoleService.addEditUserRole(userRoleVO);

	}
}
