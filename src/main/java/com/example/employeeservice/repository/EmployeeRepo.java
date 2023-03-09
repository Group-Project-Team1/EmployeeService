package com.example.employeeservice.repository;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.VisaStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, Integer> {

    Employee findEmployeeById(Integer id);

    void deleteById(Integer id);

    List<Employee> findByEmailContains(String emailSeg);


    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrPreferredNameContainingIgnoreCase(String name1, String name2, String name3, String name4);

    Employee findEmployeeByEmail(String email);

    List<Employee> findEmployeesByHouseId(Integer houseId);
}
