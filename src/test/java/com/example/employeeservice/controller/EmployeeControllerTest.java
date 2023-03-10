package com.example.employeeservice.controller;
import static org.junit.Assert.assertEquals;
import com.example.employeeservice.domain.entity.*;
import com.example.employeeservice.domain.response.EmployeeProfile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;
import com.example.employeeservice.service.EmployeeProfileService;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;

@SpringBootTest()
@Slf4j
public class EmployeeControllerTest {
    private EmployeeController employeeController;

    @Mock
    private EmployeeProfileService employeeProfileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeController = new EmployeeController(employeeProfileService);
    }

    @Test
    public void testFindEmployeeProfileById() throws Exception {
        String id = "123";
        EmployeeProfile employeeProfile = new EmployeeProfile();
        Mockito.when(employeeProfileService.findEmployeeProfileById(id)).thenReturn(employeeProfile);
        ResponseEntity<Object> response = employeeController.findEmployeeProfileById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeProfile, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        EmployeeProfile employeeProfile = new EmployeeProfile();
        Mockito.when(employeeProfileService.updateEmployee(employee)).thenReturn(employeeProfile);
        ResponseEntity<Object> response = employeeController.updateEmployee(employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeProfile, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    public void testUpdateProfile() throws Exception {
        Integer id = 1;
        String key = "firstName";
        String val = "ff";
        EmployeeProfile employeeProfile = new EmployeeProfile();
        when(employeeProfileService.updateProfile(id, key, val)).thenReturn(employeeProfile);
        ResponseEntity<Object> response = employeeController.updateProfile(id, key, val);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeProfile, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    void testAddContact() {
        Integer id = 100;
        Employee employee = new Employee();
        employee.setId(id);
        Contact contact = new Contact("系人","联");
        when(employeeProfileService.findEmployeeById(id)).thenReturn(employee);
        ResponseEntity<Object> response = employeeController.addContact(100, contact);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contact, ((HashMap<String, Object>)response.getBody()).get("data"));
    }


    @Test
    public void testAddAddress() throws Exception {
        Integer id = 100;
        Employee employee = new Employee();
        employee.setId(id);
        Address address = new Address(100, "l1", "l2", "city", "state", "zip");
        when(employeeProfileService.findEmployeeById(id)).thenReturn(employee);
        ResponseEntity<Object> response = employeeController.addAddress(100, address);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(address, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    public void testAddVisaStatus() throws Exception {
        Integer id = 100;
        Employee employee = new Employee();
        employee.setId(id);
        VisaStatus visaStatus = new VisaStatus(true, LocalDate.of(2018, 9, 1), LocalDate.of(2023, 9, 1), LocalDate.parse("2022-09-01"));
        when(employeeProfileService.findEmployeeById(id)).thenReturn(employee);
        ResponseEntity<Object> response = employeeController.addVisaStatus(100, visaStatus);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(visaStatus, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    public void testAddPersonalDocument() throws Exception {
        Integer id = 100;
        Employee employee = new Employee();
        employee.setId(id);
        PersonalDocument personalDocument = new PersonalDocument("I-20");
        when(employeeProfileService.findEmployeeById(id)).thenReturn(employee);
        ResponseEntity<Object> response = employeeController.addPersonalDocument(100, personalDocument);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personalDocument, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    public void testFindEmployeeById() {
        Employee employee = new Employee();
        employee.setId(666);
        employee.setFirstName("名字");
        employee.setLastName("姓氏");
        employee.setEmail("xingming@hao123.com");
        employee.setVisaStatuses(Collections.emptyList());
        when(employeeProfileService.findEmployeeById(666)).thenReturn(employee);
        Employee e = employeeController.findEmployeeById(666);
        Assertions.assertEquals(employee, e);
    }
}