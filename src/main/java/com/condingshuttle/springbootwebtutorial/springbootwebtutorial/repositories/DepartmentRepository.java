package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

}
