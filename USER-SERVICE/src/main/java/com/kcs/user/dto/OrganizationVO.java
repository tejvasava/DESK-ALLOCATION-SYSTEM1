package com.kcs.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationVO {

	private Long id;

	private String name;
	
	private Boolean status;
}
