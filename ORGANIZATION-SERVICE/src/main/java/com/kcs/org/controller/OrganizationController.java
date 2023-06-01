package com.kcs.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcs.org.dto.OrganizationVO;
import com.kcs.org.dto.ResponseVO;
import com.kcs.org.service.OrganizationService;

@RestController
@RequestMapping("/Orgnization")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	@PostMapping("/addEdit")
	public ResponseVO<?> addEditOrganization(@RequestBody OrganizationVO organizationVo)
	{
		return organizationService.addEditOrganization(organizationVo);
		
	}
	
	@DeleteMapping("/deleteOrg")
	public ResponseVO<?> deleteOrganization(@RequestParam ("id") Long id)
	{
		return organizationService.deleteOrganization(id);
	}
	
	@GetMapping("/getById")
	public ResponseVO<OrganizationVO> getOrganizationById(@RequestParam ("id") Long id)
	{
		return organizationService.getOrganizationById(id);
	}
	
	@GetMapping("/getAllOrganization")
	public List<OrganizationVO> getAllOrganization()
	{
		return organizationService.getAllOrganization();
	}
	
	@GetMapping("/getByIdVOfrom")
	public OrganizationVO getOrganizationByVOForm(@RequestParam ("id") Long id)
	{
		return organizationService.getOrganizationByVOForm(id);
	}
}
