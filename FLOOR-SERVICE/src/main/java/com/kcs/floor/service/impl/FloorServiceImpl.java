package com.kcs.floor.service.impl;

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

import com.kcs.floor.core.Floor;
import com.kcs.floor.dto.FloorDto;
import com.kcs.floor.dto.ResponseVO;
import com.kcs.floor.repository.FloorRepository;
import com.kcs.floor.service.FloorService;
import com.kcs.floor.dto.ResponseStatus;

@Service
public class FloorServiceImpl implements FloorService{

	@Autowired
	private FloorRepository floorRepository;
	
	@Override
	public ResponseVO<?> addEditFloor(FloorDto floorDto) {
		ResponseVO<?> vo=validateRequest(floorDto);
		
		Floor floor=new Floor();
		if(vo==null)
		{
			if(!ObjectUtils.isEmpty(floorDto.getId())) {
				floor.setId(floorDto.getId());
				floor.setName(floorDto.getName());
				floor.setOrgId(floorDto.getOrgId());
				floor.setStatus(floorDto.getStatus());
				floorRepository.save(floor);
				return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Updated !!",floor);
			}

			floor.setName(floorDto.getName());
			floor.setOrgId(floorDto.getOrgId());
			floor.setStatus(floorDto.getStatus());
			floorRepository.save(floor);
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Added !!",floor);
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.FAIL.name(), "NOT ADDED !");
	}

	private ResponseVO<?> validateRequest(FloorDto floorDto) {
		if(StringUtils.isBlank(floorDto.getName()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"floor name cant't be blank");
		}
		if(floorDto.getOrgId()==null)
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"organization id can't be  blank");
		}
		return null;
	}

	@Override
	public ResponseVO<?> deleteFloor(Long id) {
		
		Optional<Floor> floorOpt=floorRepository.findById(id);
		Floor floor=new Floor();
		if(!floorOpt.isEmpty())
		{
			floor=floorOpt.get();
			floor.setStatus(false);
			floorRepository.save(floor);
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Deleted !!",floor);
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Error Deleteing !!",floor);
	}

	@Override
	public FloorDto getFloor(Long id) {
		Optional<Floor> floorOpt=floorRepository.findById(id);
		if(floorOpt.isPresent())
		{
			FloorDto floorDto=new FloorDto();
			floorDto.setId(floorOpt.get().getId());
			floorDto.setName(floorOpt.get().getName());
			floorDto.setOrgId(floorOpt.get().getOrgId());
			floorDto.setStatus(floorOpt.get().getStatus());
			return floorDto;
		}
		return null;
	}

	@Override
	public ResponseVO<List<FloorDto>> getAllFloor() {
		
		List<Floor> floorList=floorRepository.findAll();
		
		List<FloorDto> floorVO=new ArrayList<FloorDto>();
		
		if (!CollectionUtils.isEmpty(floorList)) {
			
			floorVO = floorList.stream().map(obj -> convertToVO(obj, false)).collect(Collectors.toList());
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully get all floor !!",floorVO);
		
		
	}

	private FloorDto convertToVO(Floor floor, boolean b) {
		Floor floorVo=floorRepository.getById(floor.getId());
		FloorDto vo=new FloorDto();
		vo.setId(floorVo.getId());
		vo.setName(floorVo.getName());
		vo.setOrgId(floorVo.getOrgId());
		vo.setStatus(floorVo.getStatus());
		return vo;
	}

}
