package com.kcs.floor.dto;

import lombok.Data;

@Data
public class FloorDto {

	private Long id;
	
	private String name;
	
	private Long orgId;
	
	private Boolean status;
}
