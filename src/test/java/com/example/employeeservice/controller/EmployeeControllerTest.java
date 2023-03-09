package com.example.employeeservice.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.employeeservice.controller.EmployeeController;
import com.example.employeeservice.domain.entity.Address;
import com.example.employeeservice.domain.entity.Contact;
import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.PersonalDocument;
import com.example.employeeservice.domain.entity.VisaStatus;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.domain.response.ResponseHandler;
import com.example.employeeservice.service.EmployeeProfileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest()
@Slf4j
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeProfileService employeeProfileService;

    @InjectMocks
    private EmployeeController employeeController;


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testUpdateEmployee() {
        // Setup
        Employee employee = new Employee();
        EmployeeProfile employeeProfile = new EmployeeProfile();
        when(employeeProfileService.updateEmployee(employee)).thenReturn(employeeProfile);

        // Test
        ResponseEntity<Object> response = employeeController.updateEmployee(employee);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeProfile, response.getBody());
    }

    @Test
    public void testUpdateProfile() {
        // Setup
        Integer id = 1;
        String key = "firstName";
        String val = "John";
        EmployeeProfile employeeProfile = new EmployeeProfile();
        when(employeeProfileService.updateProfile(id, key, val)).thenReturn(employeeProfile);

        // Test
        ResponseEntity<Object> response = employeeController.updateProfile(id, key, val);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeProfile, response.getBody());
    }
}