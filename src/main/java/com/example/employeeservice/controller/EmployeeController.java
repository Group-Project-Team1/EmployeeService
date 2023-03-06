package com.example.employeeservice.controller;

import com.example.employeeservice.domain.entity.*;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employee")
//@Api(value = "EmployeeController RESTful endpoints")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // get -> find
    @GetMapping("/find")
    public Employee findEmployeeById(@PathParam("id") Integer id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/viewProfile")
    public EmployeeProfile viewProfileById(@PathParam("id") Integer id) {
        return new EmployeeProfile(employeeService.findEmployeeById(id));
    }

    @GetMapping("/findByEmail")
    public Employee findEmployeeByEmail(@PathParam("email") String email) {
        List<Employee> employees = employeeService.findAllEmployees();
        return employees.stream().filter(e -> e.getEmail().equals(email)).findFirst().get();
    }

    @GetMapping("/viewProfileByEmail")
    public EmployeeProfile viewProfileByEmail(@PathParam("email") String email) {
        Employee employee = employeeService.findEmployeeByEmail(email);
        return new EmployeeProfile(employee);
    }

    // post -> add
    // avoid duplicate email
    @PostMapping("/add")
    @ApiOperation("save an employee to MongoDB")
    public void addEmployee(@RequestBody Employee employee) {
        List<Employee> employees = employeeService.findAllEmployees();
        Employee rep = employees.stream().filter(e -> e.getEmail().equals(employee.getEmail())).findFirst().orElseGet(() -> null);
        if (rep == null) {
            employeeService.saveEmployee(employee);
        }
    }

    //put -> update
    @PutMapping("/update")
    public void updateProfile(@PathParam("id") Integer id, @PathParam("key") String key, @PathParam("val") String val) throws ParseException {
        Employee employee = employeeService.findEmployeeById(id);
        key = key.toLowerCase();
        if (key.equals("firstname")) {
            employee.setFirstName(val);
        }
        else if (key.equals("lastname")) {
            employee.setLastName(val);
        }
        else if (key.equals("middlename")) {
            employee.setMiddleName(val);
        }
        else if (key.equals("preferredname")) {
            employee.setPreferredName(val);
        }
        else if (key.equals("email")) {
            employee.setEmail(val);
        }
        else if (key.equals("cellphone")) {
            employee.setCellPhone(val);
        }
        else if (key.equals("alternatephone")) {
            employee.setAlternatePhone(val);
        }
        else if (key.equals("gender")) {
            employee.setGender(val);
        }
        else if (key.equals("ssn")) {
            employee.setSsn(val);
        }
        else if (key.equals("dob")) {
            employee.setDriverLicenseExpiration(LocalDate.parse(val));
        }
        else if (key.equals("driverlicense")) {
            employee.setDriverLicense(val);
        }
        else if (key.equals("driverlicenseexpiration")) {
            employee.setDriverLicenseExpiration(LocalDate.parse(val));
        }

        employeeService.saveEmployee(employee);
    }


//    //update address, contact
//    public void update() {
//
//    }
//


    @PostMapping("/addContact")
    public void addContact(@PathParam("id") Integer id, @RequestBody Contact contact) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.getContacts().add(contact);
        employeeService.saveEmployee(employee);
    }

    @PostMapping("/addAddress")
    public void addAddress(@PathParam("id") Integer id, @RequestBody Address address) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.getAddresses().add(address);
        employeeService.saveEmployee(employee);
    }

    @PostMapping("/addVisaStatus")
    public void addVisaStatus(@PathParam("id") Integer id, @RequestBody VisaStatus visaStatus) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.getVisaStatuses().add(visaStatus);
        employeeService.saveEmployee(employee);
    }

    @PostMapping("/addPersonalDocument")
    public void addPersonalDocument(@PathParam("id") Integer id, @RequestBody PersonalDocument personalDocument) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.getPersonalDocuments().add(personalDocument);
        employeeService.saveEmployee(employee);
    }






//
//    public void updateFile() {
//
//    }
//
//    public void addFile() {
//
//    }
//
//    //delete -> remove




}
