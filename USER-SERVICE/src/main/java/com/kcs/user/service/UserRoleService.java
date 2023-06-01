package com.kcs.user.service;

import com.kcs.user.dto.ResponseVO;
import com.kcs.user.dto.UserRoleVO;

public interface UserRoleService {

	ResponseVO<?> addEditUserRole(UserRoleVO userRoleVO);

}
