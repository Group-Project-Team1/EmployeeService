package com.example.employeeservice.controller;

import com.example.employeeservice.domain.entity.*;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.domain.response.ResponseHandler;
import com.example.employeeservice.security.JwtProvider;
import com.example.employeeservice.service.EmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeProfileService employeeProfileService;

    @Autowired
    public EmployeeController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    //6. c find profile by id
    @GetMapping("/findById")
    public ResponseEntity<Object> findEmployeeProfileById(@PathParam("id") String id) {
        EmployeeProfile employeeProfile = employeeProfileService.findEmployeeProfileById(id);
        return ResponseHandler.generateResponse(
                "Found the employee profile.",
                HttpStatus.OK,
                employeeProfile
        );
    }

    //6.c. update an employee (profile)
    @PostMapping("/updateEmployee")
    @PreAuthorize("hasAuthority('employee')")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
        EmployeeProfile employeeProfile = employeeProfileService.updateEmployee(employee);
        return ResponseHandler.generateResponse(
                "Updated an employee successfully.",
                HttpStatus.OK,
                employeeProfile
        );
    }

    //6.c. update profile
    @PutMapping("/update")
    public ResponseEntity<Object> updateProfile(@PathParam("userId") Integer userId, @PathParam("key") String key, @PathParam("val") String val) {
        EmployeeProfile employeeProfile = employeeProfileService.updateProfile(userId, key, val);
        return ResponseHandler.generateResponse(
                "Updated employee profile successfully.",
                HttpStatus.OK,
                employeeProfile
        );
    }

    // 6.C. update profile - add to list
    @PostMapping("/addContact")
    public ResponseEntity<Object> addContact(@PathParam("id") Integer id, @RequestBody Contact contact) {
        employeeProfileService.addContact(id, contact);
        return ResponseHandler.generateResponse(
                "Added a contact successfully.",
                HttpStatus.OK,
                contact
        );
    }

    @PostMapping("/addAddress")
    public ResponseEntity<Object> addAddress(@PathParam("id") Integer id, @RequestBody Address address) {
        employeeProfileService.addAddress(id, address);
        return ResponseHandler.generateResponse(
                "Added an address successfully.",
                HttpStatus.OK,
                address
        );
    }

    @PostMapping("/addVisaStatus")
    public ResponseEntity<Object> addVisaStatus(@PathParam("id") Integer id, @RequestBody VisaStatus visaStatus) {
        employeeProfileService.addVisaStatus(id, visaStatus);
        return ResponseHandler.generateResponse(
                "Added a visaStatus successfully.",
                HttpStatus.OK,
                visaStatus
        );
    }

    @PostMapping("/{id}/addPersonalDocument")
    @PreAuthorize("hasAuthority('employee')")
    public ResponseEntity<Object> addPersonalDocument(@PathVariable("id") Integer id, @RequestBody PersonalDocument personalDocument) {
        employeeProfileService.addPersonalDocument(id, personalDocument);
        return ResponseHandler.generateResponse(
                "Added a document successfully.",
                HttpStatus.OK,
                personalDocument
        );
    }

    @GetMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('employee')")
    public Employee findEmployeeById(@PathVariable Integer employeeId){
        return employeeProfileService.findEmployeeById(employeeId);
    }


}
