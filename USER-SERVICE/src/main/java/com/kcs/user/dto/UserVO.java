package com.kcs.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kcs.user.core.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserVO {

	private Long id;

	private Long userRole;
	
	private String userRoleName;

	private String username;

	private String password;

	private String email;

	private String name;
	
	private String phoneNo;
	
	private Boolean status;
	
	private  Long  organizationId;
	
	private  Long  deskId;
	
	private  Long  floorId;
	
}
