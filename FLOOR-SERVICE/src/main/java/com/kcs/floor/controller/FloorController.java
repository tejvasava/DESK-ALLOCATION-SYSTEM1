package com.kcs.floor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcs.floor.dto.FloorDto;
import com.kcs.floor.dto.ResponseVO;
import com.kcs.floor.service.FloorService;

@RestController
@RequestMapping("/floor")
public class FloorController {

	@Autowired
	private FloorService floorService;
	
	@PostMapping("/addEdit")
	public ResponseVO<?> addEditFloor(@RequestBody FloorDto floorDto)
	{
		return floorService.addEditFloor(floorDto);
	}
	
	@DeleteMapping("/deleteFloor")
	public ResponseVO<?> deleteFloor(@RequestParam ("id") Long id)
	{
		return floorService.deleteFloor(id);
		
	}
	
	@GetMapping("/getfloorById")
	public FloorDto getFloor(@RequestParam ("id") Long id)
	{
		return floorService.getFloor(id);
		
	}
	
	@GetMapping("/getAllFloor")
	public ResponseVO<List<FloorDto>> getAllFloor()
	{
		return floorService.getAllFloor();
		
	}
}
