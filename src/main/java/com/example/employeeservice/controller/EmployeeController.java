package com.example.employeeservice.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Api(value = "EmployeeController RESTful endpoints")
public class EmployeeController {
}
