package com.example.employeeservice.repository;

import com.example.employeeservice.domain.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, Integer> {

    Employee findEmployeeById(Integer id);

    void deleteById(Integer id);




}
