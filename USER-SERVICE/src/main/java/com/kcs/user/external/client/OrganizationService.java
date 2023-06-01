package com.kcs.user.external.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kcs.user.dto.OrganizationVO;
import com.kcs.user.dto.ResponseVO;

@FeignClient(name = "ORGANIZATION-SERVICE/Orgnization")
public interface OrganizationService {

	@GetMapping("/getById")
	public ResponseVO<OrganizationVO> getOrganizationById(@RequestParam ("id") Long id);
	
	
	@GetMapping("/getAllOrganization")
	public List<OrganizationVO> getAllOrganization();
	
	@GetMapping("/getByIdVOfrom")
	public OrganizationVO getOrganizationByVOForm(@RequestParam ("id") Long id);
}
