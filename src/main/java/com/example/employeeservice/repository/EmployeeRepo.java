package com.example.employeeservice.repository;

import com.example.employeeservice.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, Integer> {

    Employee findEmployeeById(Integer id);

    void deleteById(Integer id);

}
