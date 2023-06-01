package com.kcs.desk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcs.desk.core.Desk;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long>{

}
