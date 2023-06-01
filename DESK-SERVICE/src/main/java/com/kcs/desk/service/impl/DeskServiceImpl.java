package com.kcs.desk.service.impl;

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

import com.kcs.desk.core.Desk;
import com.kcs.desk.dto.DeskDto;
import com.kcs.desk.dto.DeskOfficeDto;
import com.kcs.desk.dto.OfficeDto;
import com.kcs.desk.dto.ResponseStatus;
import com.kcs.desk.dto.ResponseVO;
import com.kcs.desk.external.client.OfficeService;
import com.kcs.desk.repository.DeskRepository;
import com.kcs.desk.service.DeskService;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class DeskServiceImpl implements DeskService{

	@Autowired
	private DeskRepository deskRepository;
	
	@Autowired
	private OfficeService officeService;
	
	
	@Override
	public ResponseVO<?> addEditDesk(DeskDto deskDto) {
		
		ResponseVO<?> vo=validateRequest(deskDto);
		
		Desk desk=new Desk();
		if(vo==null)
		{
			if (!ObjectUtils.isEmpty(deskDto.getId())) {
				log.info("Updating desk Request: {}", deskDto);
				desk.setOfficeId(deskDto.getOfficeId());
				desk.setId(deskDto.getId());
				desk.setName(deskDto.getName());
				desk.setStatus(deskDto.getStatus());
				deskRepository.save(desk);
				return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Updated !!",desk);
			}
			log.info("Adding desk Request: {}", deskDto);
			desk.setOfficeId(deskDto.getOfficeId());
			desk.setName(deskDto.getName());
			desk.setStatus(deskDto.getStatus());
			deskRepository.save(desk);
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Added !!",desk);
		}
		
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.FAIL.name(), "Failed to add !!");
	}

	private ResponseVO<?> validateRequest(DeskDto deskDto) {
		
		if(StringUtils.isBlank(deskDto.getName()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Desk Name cant't be blank");
		}
		
		if(deskDto.getOfficeId()==null)
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"floor id cant't be blank");
		}
		
		return null;
	}

	@Override
	public ResponseVO<?> getDeskById(Long id) {
		
		Optional<Desk> deskOpt = deskRepository.findById(id);
		
		DeskOfficeDto deskofficeDto=new DeskOfficeDto();
		
		if(deskOpt.isPresent())
		{
			//DeskDto floor=floorService.getFloor(deskOpt.get().getFloor_id());
			log.info("Calling office Service to Add the Desk");
			ResponseVO<OfficeDto> office=officeService.getOffice(deskOpt.get().getOfficeId());
			
			deskofficeDto.setId(deskOpt.get().getId());
			deskofficeDto.setName(deskOpt.get().getName());
			deskofficeDto.setStatus(deskOpt.get().getStatus());
			deskofficeDto.setOfficeDto(office);
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Sucssfully found !!",deskofficeDto);
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.FAIL.name(), "Not found !!",deskofficeDto);
	}

	@Override
	public ResponseVO<?> deleteDeskById(Long id) {
		Optional<Desk> deskOpt = deskRepository.findById(id);
		Desk desk=new Desk();
		if(!deskOpt.isEmpty())
		{
			desk=deskOpt.get();
			desk.setStatus(false);
			deskRepository.save(desk);
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Deleted  !!",desk);
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.FAIL.name(), "Error in  Delete  !!",desk);
	}

	@Override
	public ResponseVO<List<DeskDto>> getAllDesk() {
		
		List<Desk> deskList=deskRepository.findAll();
		
		List<DeskDto> deskDtoList=new ArrayList<DeskDto>();
		
		if (!CollectionUtils.isEmpty(deskList)) {
			
			deskDtoList = deskList.stream().map(obj -> convertToVO(obj, false)).collect(Collectors.toList());
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully get all Desks !!",deskDtoList);
	
	}

	private DeskDto convertToVO(Desk desk, boolean b) {
		
		Desk deskCore=deskRepository.getById(desk.getId());
		DeskDto vo=new DeskDto();
		vo.setOfficeId(deskCore.getOfficeId());
		vo.setId(deskCore.getId());
		vo.setName(deskCore.getName());
		vo.setStatus(deskCore.getStatus());
		return vo;
	}

	@Override
	public DeskDto getDesk(Long id) {
		Desk deskCore=deskRepository.getById(id);
		DeskDto vo=new DeskDto();
		vo.setOfficeId(deskCore.getOfficeId());
		vo.setId(deskCore.getId());
		vo.setName(deskCore.getName());
		vo.setStatus(deskCore.getStatus());
		return vo;
	}

	

	

	

	
}
