package com.example.employeeservice.controller;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hr")
public class HrController {

    private final EmployeeService employeeService;

    @Autowired
    public HrController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get -> find
    // find all employees (and HRs)
    @GetMapping("/findAll")
    @ApiOperation(value = "get all employees", response = Iterable.class)
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/findAllPaginated")
    public List<Employee> getAllOrdersPaginated(@PathParam("page") String page, @PathParam("itemsPerPage") String itemsPerPage) {
        System.out.println(page);
        System.out.println(itemsPerPage);
        return employeeService.getAllEmployeesPaginated(Integer.parseInt(page), Integer.parseInt(itemsPerPage));
    }

//    public List<Employee> findAllEmployeesPaginated() {
//        return employeeService.findAllEmployees();
//    }
//
//    public List<Employee> findAllEmployeesFiltered() {
//        return employeeService.findAllEmployees();
//    }
//
//    public Employee findEmployeeByName() {

//    }

}
