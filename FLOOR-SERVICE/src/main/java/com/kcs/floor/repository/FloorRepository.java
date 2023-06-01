package com.kcs.floor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcs.floor.core.Floor;

public interface FloorRepository extends JpaRepository<Floor, Long> {

}
