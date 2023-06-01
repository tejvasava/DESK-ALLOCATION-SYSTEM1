package com.kcs.desk.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kcs.desk.dto.OfficeDto;
import com.kcs.desk.dto.ResponseVO;
import com.kcs.desk.exception.CustomException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


//@CircuitBreaker(name="external", fallbackMethod="fallback")
@FeignClient(name = "OFFICE-SERVICE/office")
public interface OfficeService {

	@GetMapping("/getById")
	public ResponseVO<OfficeDto> getOffice(@RequestParam ("id") Long id);
	
	/*
	 * default void fallback(Exception e) { throw new
	 * CustomException("OFFICE SERVICE IS UN-AVAILABLE",e.getMessage(), 500); }
	 */
	
}
