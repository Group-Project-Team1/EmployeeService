package com.example.employeeservice.controller;

import static org.junit.Assert.assertEquals;
import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.response.EmployeeProfile;
import lombok.extern.slf4j.Slf4j;
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

//    @Test
//    public void findEmployeeById() throws Exception {
//        Mockito.when(employeeProfileService.findEmployeeById(1)).thenReturn(Employee.builder().build());
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//                .get("employee/{employeeId}", 1)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        ResultActions perform = mockMvc.perform(request);
//    }

    @Test
    public void testUpdateEmployee() throws Exception {
        // Setup
        Employee employee = new Employee();
        EmployeeProfile employeeProfile = new EmployeeProfile();
        Mockito.when(employeeProfileService.updateEmployee(employee)).thenReturn(employeeProfile);

        // Test
        ResponseEntity<Object> response = employeeController.updateEmployee(employee);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeProfile, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    public void testUpdateProfile() throws Exception {
        // Setup
        Integer id = 1;
        String key = "firstName";
        String val = "ff";
        EmployeeProfile employeeProfile = new EmployeeProfile();
        when(employeeProfileService.updateProfile(id, key, val)).thenReturn(employeeProfile);

        // Test
        ResponseEntity<Object> response = employeeController.updateProfile(id, key, val);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeProfile, ((HashMap<String, Object>)response.getBody()).get("data"));
    }
}