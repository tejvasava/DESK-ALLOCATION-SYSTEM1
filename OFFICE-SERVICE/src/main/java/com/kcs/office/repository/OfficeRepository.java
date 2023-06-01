package com.kcs.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcs.office.core.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {

}
