package com.kcs.desk.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DeskOfficeDto {
	
	//private DeskDto deskDto;
	
	private Long id;

	private String name;
	
	private Boolean status;
	
	//private OfficeDto officeDto;
	
	ResponseVO<OfficeDto> officeDto;
}
