package com.kcs.user.external.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kcs.user.dto.FloorDto;


@FeignClient(name = "FLOOR-SERVICE/floor")
public interface FloorService {

	@GetMapping("/getfloorById")
	public FloorDto getFloor(@RequestParam ("id") Long id);
}
