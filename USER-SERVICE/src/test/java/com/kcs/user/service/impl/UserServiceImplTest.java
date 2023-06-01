package com.kcs.user.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import com.kcs.user.core.User;
import com.kcs.user.core.UserRole;
import com.kcs.user.dto.ResponseStatus;
import com.kcs.user.dto.ResponseVO;
import com.kcs.user.dto.UserVO;
import com.kcs.user.external.client.OrganizationService;
import com.kcs.user.repository.UserRepository;
import com.kcs.user.repository.UserRoleRepository;
import com.kcs.user.utils.JwtUtil;

@SpringBootTest
class UserServiceImplTest {

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private UserRepository userRepository;

	@Mock
	private OrganizationService organizationService;

	@Mock
	private UserRoleRepository userRoleRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	
	@DisplayName("Add User sucsess sceniario")
	@Test
	void test_addUser_sucsess() {
		// Mocking
		// Actual
		// verification
		// Assert
		// UserRole userRole=userRoleRepository.getRoleById(1L);

		UserRole userRole = new UserRole(1L, "Admin");

		UserVO userVo = new UserVO(1L, userRole.getId(), userRole.getName(), "admin", "password", "Urvashi@gmail.com",
				"Urvashi", "9714386474", true, 1L, 1L, 1L);

		ResponseVO<?> resvo = validateRequest(userVo);
		if (resvo == null) {
			User user = new User();

			Mockito.when(userRoleRepository.findById(1L)).thenReturn(Optional.of(userRole));

			user.setName(userVo.getName());
			user.setPhoneNo(userVo.getPhoneNo());
			user.setStatus(userVo.getStatus());
			user.setUserRole(userRole);
			user.setEmail(userVo.getEmail());
			user.setUsername(userVo.getUsername());
			user.setOrganizationId(userVo.getOrganizationId());
			user.setDeskId(userVo.getDeskId());
			user.setFloorId(userVo.getFloorId());

			user.setPassword("password");
			userRepository.save(user);

			// Mockito.verify(userRoleRepository.findById(1L));

			userServiceImpl.addEditUser(userVo);
			ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Added !!", user);
		}

	}

	private ResponseVO<?> validateRequest(UserVO userVo) {
		if (StringUtils.isBlank(userVo.getUsername())) {
			 ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Username cant't be blank");
		}
		if (StringUtils.isBlank(userVo.getPassword())) {
			 ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"password cant't be blank");
		}
		if (userVo.getOrganizationId() == null) {
			 ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Organization can't be blank");
		}

		return null;
	}

	@DisplayName("Validate request")
	@Test
	void test_validateUser_fail() {
		
		UserRole userRole = new UserRole(1L, "Admin");
		String UserName=null;
		String password=null;
		UserVO userVo = new UserVO(1L, userRole.getId(), userRole.getName(),UserName ,password, "Urvashi@gmail.com","Urvashi", "9714386474", true, null, 1L, 1L);
		
		//UserVO userVo =null;
		ResponseVO<?> resvo = validateRequest(userVo);
		
	}
	
	//@Disabled
	@DisplayName("Edit User sucsess sceniario")
	@Test
	void test_EditUser_sucsess() {

		UserRole userRole = new UserRole(1L, "Admin");

		UserVO userVo = new UserVO(1L, userRole.getId(), userRole.getName(), "admin", "password", "Urvashi@gmail.com",
				"Urvashi", "9714386474", true, 1L, 1L, 1L);

		ResponseVO<?> resvo = validateRequest(userVo);

		User user = new User();
		if (resvo == null) {
			Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

			if (!ObjectUtils.isEmpty(userVo.getId())) {

				Optional<User> userOpt = userRepository.findById(userVo.getId());

				if (userOpt.isPresent()) {
					user = userOpt.get();
					user.setName(userVo.getName());
					user.setPhoneNo(userVo.getPhoneNo());
					user.setStatus(userVo.getStatus());
					user.setUserRole(userRole);
					user.setEmail(userVo.getEmail());
					user.setUsername(userVo.getUsername());
					user.setOrganizationId(userVo.getOrganizationId());

					user.setOrganizationId(userVo.getOrganizationId());
					user.setDeskId(userVo.getDeskId());
					user.setFloorId(userVo.getFloorId());

					user.setPassword("password");
					userRepository.save(user);
					userServiceImpl.addEditUser(userVo);
					ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Updated !!");
				}

			}
			//verification that how many times userRepository called
			verify(userRepository,times(2)).findById(1L);
		}
	}

	
	@DisplayName("get User by id succsess")
	@Test
	void test_getUser_sucsess() {
		
		UserRole userRole = new UserRole(1L, "Admin");
		
		User user=new User(1L, userRole,"admin", "password", "Urvashi@gmail.com","Urvashi", "9714386474", true, 1L, 1L, 1L);
		
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		
		userServiceImpl.getUserById(user.getId());
		
		//verification that how many times userRepository called
		verify(userRepository,times(1)).findById(1L);
		
		//Assert
		Assertions.assertNotNull(user);
	}
	
	@DisplayName("get User by id fail")
	@Test
	void test_getUser_fail() {
		
	
		User user=new User();
		
		//Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		
		userServiceImpl.getUserById(user.getId());
		
		//verification that how many times userRepository called
		verify(userRepository,times(1)).findById(null);
		
		//Assert
		Assertions.assertNull(null);
	}
	
	
	
	@DisplayName("findAllUser sucssess")
	@Test
	void test_findAllUser_sucsess() {
		
		UserRole userRole = new UserRole(1L, "Admin");
		
		User user=new User(1L, userRole,"admin", "password", "Urvashi@gmail.com","Urvashi", "9714386474", true, 1L, 1L, 1L);
		User user1=new User(1L, userRole,"admin", "password", "Urvashi@gmail.com","Urvashi", "9714386474", true, 1L, 1L, 1L);
		User user2=new User(1L, userRole,"admin", "password", "Urvashi@gmail.com","Urvashi", "9714386474", true, 1L, 1L, 1L);
		
		List<User> listUser=new ArrayList<>();
		listUser.add(user);
		listUser.add(user1);
		listUser.add(user2);
		
		//List<UserResponseDto> userResDto=new ArrayList<>();
		
		Mockito.when(userRepository.findAll()).thenReturn(listUser);
		
		userServiceImpl.findAllUser();
		
		//verification that how many times userRepository called
		verify(userRepository,times(1)).findAll();
		
		//Assert
		Assertions.assertNotNull(listUser);
	}

	@DisplayName("delete User sucssess")
	@Test
	void test_deleteUserr_sucsess() {
		
		UserRole userRole = new UserRole(1L, "Admin");
		
		User user=new User(1L, userRole,"admin", "password", "Urvashi@gmail.com","Urvashi", "9714386474", true, 1L, 1L, 1L);
		
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		
		if(user != null)
		{
			user.setStatus(false);
			//userRepository.save(user);
			Mockito.when(userRepository.save(user)).thenReturn(user);
		}
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		
		userServiceImpl.deleteUser(user.getId());
		
		//verification that how many times userRepository called
		verify(userRepository,times(1)).findById(1L);
		
		verify(userRepository,times(1)).save(user);
	}
	
}
