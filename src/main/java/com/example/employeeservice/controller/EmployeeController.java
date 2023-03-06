package com.example.employeeservice.controller;

import com.example.employeeservice.domain.*;
import com.example.employeeservice.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/employee")
//@Api(value = "EmployeeController RESTful endpoints")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    // get -> find
    @GetMapping("/find")
    public Employee findEmployeeById(@PathParam("id") Integer id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/findByEmail")
    public Employee findEmployeeByEmail(@PathParam("email") String email) {
        List<Employee> employees = employeeService.findAllEmployees();
        return employees.stream().filter(e -> e.getEmail().equals(email)).findFirst().get();
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
            employee.setSSN(val);
        }
        else if (key.equals("dob")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            employee.setDOB(dateFormat.parse(val));
        }
//        else if (key.equals("startdate")) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            employee.setStartDate(dateFormat.parse(val));
//        }
//        else if (key.equals("enddate")) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            employee.setEndDate(dateFormat.parse(val));
//        }
        else if (key.equals("driverlicense")) {
            employee.setDriverLicense(val);
        }
        else if (key.equals("driverlicenseexpiration")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            employee.setDriverLicenseExpiration(dateFormat.parse(val));
        }

        employeeService.saveEmployee(employee);
    }


//    //update address, contact
//    public void update() {
//
//    }
//
    //add addresses, contacts, etc.
//    @PostMapping("/addToList")
//    public void addToList(@PathParam("id") Integer id, @RequestBody Object object) {
//        System.out.println("dayin");
//        System.out.println(object);
//        System.out.println(object.getClass());
//        Employee employee = employeeService.findEmployeeById(id);
//        if (object instanceof Contact) {
//            System.out.println((Contact)object);
//            employee.getContacts().add((Contact) object);
//        }
//        else if (object instanceof Address) {
//            employee.getAddresses().add((Address) object);
//        }
//        else if (object instanceof VisaStatus) {
//            employee.getVisaStatuses().add((VisaStatus) object);
//        }
//        else if (object instanceof PersonalDocument) {
//            employee.getPersonalDocuments().add((PersonalDocument) object);
//        }
//        employeeService.saveEmployee(employee);
//    }

    @PostMapping("/addContact")
    public void addContact(@PathParam("id") Integer id, @RequestBody Contact contact) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.getContacts().add(contact);
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
