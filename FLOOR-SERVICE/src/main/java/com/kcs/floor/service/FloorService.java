package com.kcs.floor.service;


import java.util.List;

import com.kcs.floor.dto.FloorDto;
import com.kcs.floor.dto.ResponseVO;

public interface FloorService {

	ResponseVO<?> addEditFloor(FloorDto floorDto);

	ResponseVO<?> deleteFloor(Long id);

	FloorDto getFloor(Long id);

	ResponseVO<List<FloorDto>> getAllFloor();

}
