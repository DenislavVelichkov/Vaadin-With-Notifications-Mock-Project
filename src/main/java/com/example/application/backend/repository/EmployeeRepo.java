package com.example.application.backend.repository;

import java.util.List;
import java.util.Optional;

import com.example.application.backend.data.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

  Optional<Employee> findById(Long id);

  List<Employee> findAll();

}
