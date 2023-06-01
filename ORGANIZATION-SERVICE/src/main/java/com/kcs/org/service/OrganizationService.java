package com.kcs.org.service;

import java.util.List;

import com.kcs.org.dto.OrganizationVO;
import com.kcs.org.dto.ResponseVO;

public interface OrganizationService {

	ResponseVO<?> addEditOrganization(OrganizationVO organizationVo);

	ResponseVO<?> deleteOrganization(Long id);

	ResponseVO<OrganizationVO> getOrganizationById(Long id);

	List<OrganizationVO> getAllOrganization();
	
	OrganizationVO getOrganizationByVOForm(Long id);

}
