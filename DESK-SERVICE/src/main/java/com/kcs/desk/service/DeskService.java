package com.kcs.desk.service;

import java.util.List;

import com.kcs.desk.dto.DeskDto;
import com.kcs.desk.dto.ResponseVO;

public interface DeskService {

	ResponseVO<?> addEditDesk(DeskDto deskDto);

	ResponseVO<?> getDeskById(Long id);

	ResponseVO<?> deleteDeskById(Long id);

	ResponseVO<List<DeskDto>> getAllDesk();

	DeskDto getDesk(Long id);

}
