package com.kcs.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcs.user.core.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	//UserRole getById();

	UserRole getRoleById(Long id);

}
