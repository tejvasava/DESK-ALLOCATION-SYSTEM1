package com.kcs.user.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kcs.user.dto.DeskDto;


@FeignClient(name = "DESK-SERVICE/desk")
public interface DeskService {

	@GetMapping("/getDeskByid")
	public DeskDto getDeskById(@RequestParam ("id") Long id);

}
