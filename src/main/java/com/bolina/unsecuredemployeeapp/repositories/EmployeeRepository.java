package com.bolina.unsecuredemployeeapp.repositories;

import com.bolina.unsecuredemployeeapp.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
