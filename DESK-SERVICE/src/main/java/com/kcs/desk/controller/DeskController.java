package com.kcs.desk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcs.desk.core.Desk;
import com.kcs.desk.dto.DeskDto;
import com.kcs.desk.dto.ResponseVO;
import com.kcs.desk.service.DeskService;

@RequestMapping("/desk")
@RestController
public class DeskController {

	@Autowired
	private DeskService deskService;
	
	@PostMapping("/add")
	public  ResponseVO<?> addEditDesk(@RequestBody DeskDto deskDto)
	{
		return deskService.addEditDesk(deskDto);
	}
	
	@GetMapping("/getDesk")
	public  ResponseVO<?> getDesk(@RequestParam ("id") Long id){
		return deskService.getDeskById(id);
		
	}
	
	@DeleteMapping("/delete")
	public  ResponseVO<?> deleteDesk(@RequestParam ("id") Long id){
		return deskService.deleteDeskById(id);
		
	}
	
	@GetMapping("/getAllDesk")
	public  ResponseVO<List<DeskDto>> getDesk(){
		return deskService.getAllDesk();
		
	}
	
	
	@GetMapping("/getDeskByid")
	public DeskDto getDeskById(@RequestParam ("id") Long id) {
		return deskService.getDesk(id);
	}
}
