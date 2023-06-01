package com.kcs.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.kcs.user.core.User;
import com.kcs.user.core.UserRole;
import com.kcs.user.dto.JwtRequest;
import com.kcs.user.dto.OrganizationVO;
import com.kcs.user.dto.ResponseStatus;
import com.kcs.user.dto.ResponseVO;
import com.kcs.user.dto.UserResponseDto;
import com.kcs.user.dto.UserVO;
import com.kcs.user.external.client.DeskService;
import com.kcs.user.external.client.FloorService;
import com.kcs.user.external.client.OrganizationService;
import com.kcs.user.repository.UserRepository;
import com.kcs.user.repository.UserRoleRepository;
import com.kcs.user.service.UserService;
import com.kcs.user.utils.JwtTokenUtil;
import com.kcs.user.utils.JwtUtil;

@Service
public class UserServiceImpl implements UserService{

	//@Autowired
	//private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private FloorService floorService;
	
	@Autowired
	private DeskService deskService;
	
	@Override
	public ResponseVO<?>  addEditUser(UserVO userVo) {
		
		ResponseVO<?>  resvo=validateRequest(userVo);
		
		UserRole userRole=userRoleRepository.getRoleById(userVo.getUserRole());
		
		//FloorDto floorVo=floorService.getFloor(userVo.getFloorId());
		
		//DeskDto deskDto= deskService.getDeskById(userVo.getDeskId());
		
		//OrganizationVO orgVO= organizationService.getOrganizationByVOForm(userVo.getOrganizationId());
		
		User user=new User();
		
		if(resvo==null)
		{
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
					return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Updated !!");
				}
			}
			user.setName(userVo.getName());
			user.setPhoneNo(userVo.getPhoneNo());
			user.setStatus(userVo.getStatus());
			user.setUserRole(userRole);
			user.setEmail(userVo.getEmail());
			user.setUsername(userVo.getUsername());
			//user.setPassword(passwordEncoder.encode(userVo.getPassword()));
			user.setPassword("password");
			user.setOrganizationId(userVo.getOrganizationId());
			user.setDeskId(userVo.getDeskId());
			user.setFloorId(userVo.getFloorId());
			user.setDeskId(userVo.getDeskId());
			
			userRepository.save(user);	
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Succssfully Added !!",user);
		}
		
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.FAIL.name(), "Failed to Add User !!");
		
	}

	private ResponseVO<?> validateRequest(UserVO userVo) {
		if(StringUtils.isBlank(userVo.getUsername()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Username cant't be blank");
		}
		if(StringUtils.isBlank(userVo.getPassword()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"password cant't be blank");
		}
		if(userVo.getOrganizationId()==null)
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Organization can't be blank");
		}
		
		return null;
	}

	@Override
	public ResponseVO<?> deleteUser(Long id) {
		
		Optional<User> userOpt = userRepository.findById(id);
		User user=new User();
		if(userOpt.isPresent())
		{
			user = userOpt.get();
			user.setStatus(false);
			userRepository.save(user);
			
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Deleted sucssfully !!");
	}

	@Override
	public List<UserResponseDto> findAllUser() {
		
		List<User> listUser=userRepository.findAll();
		
		List<UserResponseDto> userResDto=new ArrayList<>();
		
		if (!CollectionUtils.isEmpty(listUser)) {
			userResDto = listUser.stream().map(user -> convertToVO(user)).collect(Collectors.toList());
			}
		return userResDto;
	}

	private UserResponseDto convertToVO(User user) {
		
		UserResponseDto userResDto=new UserResponseDto();
		OrganizationVO orgVO= organizationService.getOrganizationByVOForm(user.getOrganizationId());
		userResDto.setId(user.getId());
		userResDto.setName(user.getName());
		userResDto.setEmail(user.getEmail());
		userResDto.setOrganization(orgVO);
		userResDto.setPassword(user.getPassword());
		userResDto.setPhoneNo(user.getPhoneNo());
		userResDto.setStatus(user.getStatus());
		userResDto.setUsername(user.getUsername());
		return userResDto;
	}

	@Override
	public ResponseVO<?> getUserById(Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		
		UserResponseDto userResDto=new UserResponseDto();
		
		if(userOpt.isPresent())
		{
			OrganizationVO orgVO= organizationService.getOrganizationByVOForm(userOpt.get().getOrganizationId());
			
			userResDto.setId(userOpt.get().getId());
			userResDto.setName(userOpt.get().getName());
			userResDto.setEmail(userOpt.get().getEmail());
			userResDto.setOrganization(orgVO);
			userResDto.setPassword(userOpt.get().getPassword());
			userResDto.setPhoneNo(userOpt.get().getPhoneNo());
			userResDto.setStatus(userOpt.get().getStatus());
			userResDto.setUsername(userOpt.get().getUsername());
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "user Get sucssfully !!",userResDto);
		}
		 return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.FAIL.value(), "User Not Found !!",userResDto);
	}

	@Override
	public User getUserByUserName(String username) {
		Optional<User> user = userRepository.findFirstByUsername(username);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public ResponseVO<?> generateToken(JwtRequest authenticationRequest) {
		String token = null;
		Optional<User> user = userRepository.findFirstByUsername(authenticationRequest.getUsername());
		if (user.isPresent()) {
			//token = jwtUtil.generateToken(user.get().getUsername());
			token = jwtTokenUtil.generateToken(user.get().getUsername());
			//token = jwtUtil.generateToken("userName");
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "Token Got sucssfully !!",token);
		}
		return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "failed to get token or null !!",token);
		
	}

}
