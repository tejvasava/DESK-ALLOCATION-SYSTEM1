package com.kcs.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcs.org.core.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long>{

}
