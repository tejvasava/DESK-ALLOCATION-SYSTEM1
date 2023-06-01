package com.kcs.floor.dto;

import lombok.Data;

@Data
public class FloorResponsDto {

	private Long id;
	
	private String name;
	
	private Boolean status;
	
	private OrganizationVO organizationVO;
}
