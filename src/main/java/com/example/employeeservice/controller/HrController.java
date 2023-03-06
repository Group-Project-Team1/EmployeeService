package com.example.employeeservice.controller;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hr")
public class HrController {

    private final EmployeeService employeeService;

    @Autowired
    public HrController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get -> find
    @GetMapping("/findAll")
    @ApiOperation(value = "get all employees", response = Iterable.class)
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

//    public List<Employee> findAllEmployeesWithPagination() {
//        return employeeService.findAllEmployees();
//    }
//
//    public List<Employee> findAllEmployeesWithFilters() {
//        return employeeService.findAllEmployees();
//    }
//
//    public Employee findEmployeeByName() {

//    }

}
