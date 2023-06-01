package com.kcs.user.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kcs.user.core.User;
import com.kcs.user.dto.JwtRequest;
import com.kcs.user.dto.ResponseVO;
import com.kcs.user.dto.UserResponseDto;
import com.kcs.user.dto.UserVO;

public interface UserService {

	ResponseVO addEditUser(UserVO userVo);

	ResponseVO<?> deleteUser(Long id);

	//ResponseVO<?> findAllUser();
	
	List<UserResponseDto> findAllUser();

	ResponseVO<?> getUserById(Long id);

	User getUserByUserName(String username);

	ResponseVO<?> generateToken(JwtRequest authenticationRequest);

}
