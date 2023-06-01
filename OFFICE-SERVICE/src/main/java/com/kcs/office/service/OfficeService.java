package com.kcs.office.service;

import java.util.List;

import com.kcs.office.dto.OfficeDto;
import com.kcs.office.dto.ResponseVO;

public interface OfficeService {

	ResponseVO<?> addEditOffice(OfficeDto officeDto);

	ResponseVO<List<OfficeDto>> getAllOffices();

	ResponseVO<OfficeDto> getOffice(Long id);

}
