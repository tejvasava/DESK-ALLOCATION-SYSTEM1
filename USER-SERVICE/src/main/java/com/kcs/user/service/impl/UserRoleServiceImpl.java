package com.kcs.user.service.impl;

import org.springframework.stereotype.Service;

import com.kcs.user.dto.ResponseVO;
import com.kcs.user.dto.UserRoleVO;
import com.kcs.user.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Override
	public ResponseVO<?> addEditUserRole(UserRoleVO userRoleVO) {
		ResponseVO<?> vo=validateRequest(userRoleVO);
		
		if(vo==null)
		{
			
		}
		return null;
	}

	private ResponseVO<?> validateRequest(UserRoleVO userRoleVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
