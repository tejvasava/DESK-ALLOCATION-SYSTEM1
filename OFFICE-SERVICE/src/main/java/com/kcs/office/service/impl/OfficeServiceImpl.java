package com.kcs.office.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;


import com.kcs.office.core.Office;
import com.kcs.office.dto.OfficeDto;
import com.kcs.office.dto.ResponseStatus;
import com.kcs.office.dto.ResponseVO;
import com.kcs.office.repository.OfficeRepository;
import com.kcs.office.service.OfficeService;


@Service
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	private OfficeRepository officeRepository;
	
	@Override
	public ResponseVO<?> addEditOffice(OfficeDto officeDto) {
		
		ResponseVO<?>  resvo=validateRequest(officeDto);
		
		Office office=new Office();
		if(resvo==null)
		{
			if(!ObjectUtils.isEmpty(officeDto.getId()))
			{
				office.setId(officeDto.getId());
				office.setFloor_id(officeDto.getFloor_id());
				office.setName(officeDto.getName());
				office.setStatus(officeDto.getStatus());
				officeRepository.save(office);
				return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Updated !!",office);
			}
			office.setFloor_id(officeDto.getFloor_id());
			office.setName(officeDto.getName());
			office.setStatus(officeDto.getStatus());
			officeRepository.save(office);
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully added !!",office);
			
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Error in add or update !!",office);
	}

	private ResponseVO<?> validateRequest(OfficeDto officeDto) {
		
		if(StringUtils.isBlank(officeDto.getName()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Office Name should not be blank");
			
		}
		if(officeDto.getFloor_id()==null)
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Floor id should not be blank");
			
		}
		return null;
	}

	@Override
	public ResponseVO<List<OfficeDto>> getAllOffices() {
		
		List<Office> listOffice=officeRepository.findAll();
		
		List<OfficeDto> officeDtoList=new ArrayList<OfficeDto>();
		
		if (!CollectionUtils.isEmpty(listOffice)) {
			
			officeDtoList = listOffice.stream().map(obj -> convertToVO(obj, false)).collect(Collectors.toList());
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully get all Offices !!", officeDtoList);
	
	}

	private OfficeDto convertToVO(Office office, boolean b) {
		
		Office officeCore=officeRepository.getById(office.getId());
		OfficeDto vo=new OfficeDto();
		vo.setFloor_id(officeCore.getFloor_id());
		vo.setId(officeCore.getId());
		vo.setName(officeCore.getName());
		vo.setStatus(officeCore.getStatus());
		return vo;
	}

	@Override
	public ResponseVO<OfficeDto> getOffice(Long id) {
		Office officeCore=officeRepository.getById(id);
		OfficeDto vo=new OfficeDto();
		vo.setFloor_id(officeCore.getFloor_id());
		vo.setId(officeCore.getId());
		vo.setName(officeCore.getName());
		vo.setStatus(officeCore.getStatus());
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully get the Office!!", vo);
	}

}
