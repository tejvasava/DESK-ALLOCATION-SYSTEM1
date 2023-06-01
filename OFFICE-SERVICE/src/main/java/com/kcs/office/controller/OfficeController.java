package com.kcs.office.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcs.office.dto.OfficeDto;
import com.kcs.office.dto.ResponseVO;
import com.kcs.office.service.OfficeService;

@RestController
@RequestMapping("/office")
public class OfficeController {

	@Autowired
	private OfficeService officeService;
	
	@PostMapping("/addEditOffice")
	public ResponseVO<?> addEditOffice(@RequestBody OfficeDto officeDto)
	{
		return officeService.addEditOffice(officeDto);
	}
	
	@GetMapping("/getAll")
	public ResponseVO<List<OfficeDto>> getAllOffice()
	{
		return officeService.getAllOffices();
	}
	
	@GetMapping("/getById")
	public ResponseVO<OfficeDto> getOffice(@RequestParam ("id") Long id)
	{
		return officeService.getOffice(id);
	}
}
