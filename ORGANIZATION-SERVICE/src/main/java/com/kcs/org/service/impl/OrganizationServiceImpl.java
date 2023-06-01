package com.kcs.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.kcs.org.core.Organization;
import com.kcs.org.dto.OrganizationVO;
import com.kcs.org.dto.ResponseStatus;
import com.kcs.org.dto.ResponseVO;
import com.kcs.org.repository.OrganizationRepository;
import com.kcs.org.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Override
	public ResponseVO<?> addEditOrganization(OrganizationVO organizationVo) {

		ResponseVO<?> vo = validateRequest(organizationVo);
		if (vo == null) {
			Organization org = new Organization();
			if (!ObjectUtils.isEmpty(organizationVo.getId())) {

				Optional<Organization> userOpt = organizationRepository.findById(organizationVo.getId());
				if(!userOpt.isEmpty())
				{
					org = userOpt.get();
					org.setName(organizationVo.getName());
					org.setStatus(organizationVo.getStatus());
					organizationRepository.save(org);
					return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(),
							"Succssfully Updated !!");
				}
			}
			org.setName(organizationVo.getName());
			org.setStatus(organizationVo.getStatus());
			organizationRepository.save(org);
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Added !!");

		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.FAIL.name(),
				"Failed to Add or Update !!");
	}

	private ResponseVO<?> validateRequest(OrganizationVO organizationVo) {
		if(StringUtils.isBlank(organizationVo.getName()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Username cant't be blank");
		}
		/*
		 * if(StringUtils.isBlank(organizationVo.getStatus())) { return
		 * ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(),
		 * ResponseStatus.FAIL.name(), "password cant't be blank"); }
		 */
		
		return null;
	}

	@Override
	public ResponseVO<?> deleteOrganization(Long id) {
		
		Organization org = new Organization();
		Optional<Organization> userOpt = organizationRepository.findById(id);
		if(!userOpt.isEmpty())
		{
			org = userOpt.get();
			org.setStatus(false);
			organizationRepository.save(org);
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(),
					"Succssfully Deleted !!");
		}
		
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.FAIL.name(),
				"Failed to Deleted !!");
	}

	@SuppressWarnings("deprecation")
	@Override
	public ResponseVO<OrganizationVO> getOrganizationById(Long id) {
		// TODO Auto-generated method stub
		Organization org=organizationRepository.getById(id);
		OrganizationVO vo=new OrganizationVO();
		vo.setId(org.getId());
		vo.setName(org.getName());
		vo.setStatus(org.getStatus());
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "got succssfully !!",vo);
	}

	@Override
	public List<OrganizationVO> getAllOrganization() {
		List<Organization> orgList=organizationRepository.findAll();
		//return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "got succssfully !!",orgList);
		List<OrganizationVO> orgVO=new ArrayList<OrganizationVO>();
		if (!CollectionUtils.isEmpty(orgList)) {
			orgVO = orgList.stream().map(obj -> convertToVO(obj, false)).collect(Collectors.toList());
			}
		return orgVO;
	}

	private OrganizationVO convertToVO(Organization obj, boolean b) {
		Organization org=organizationRepository.getById(obj.getId());
		OrganizationVO vo=new OrganizationVO();
		vo.setId(org.getId());
		vo.setName(org.getName());
		vo.setStatus(org.getStatus());
		return vo;
	}

	@Override
	public OrganizationVO getOrganizationByVOForm(Long id) {
		Organization org=organizationRepository.getById(id);
		OrganizationVO vo=new OrganizationVO();
		vo.setId(org.getId());
		vo.setName(org.getName());
		vo.setStatus(org.getStatus());
		return vo;
	}

}

