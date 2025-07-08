package com.avijava.employee.repository;

import com.avijava.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
