package com.kcs.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserResponseDto {

	private Long id;

	//private Long userRole;
	
	//private String userRoleName;
	
	private UserRoleVO userRoleVO;

	private String username;

	private String password;

	private String email;

	private String name;
	
	private String phoneNo;
	
	private Boolean status;
	
	private OrganizationVO organization;
}
