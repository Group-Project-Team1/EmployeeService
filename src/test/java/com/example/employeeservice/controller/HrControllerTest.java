package com.example.employeeservice.controller;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.domain.response.EmployeeSummary;
import com.example.employeeservice.domain.response.VisaStatusResponse;
import com.example.employeeservice.service.HrHomepageService;
import com.example.employeeservice.service.HrEmployeeProfilesService;
import com.example.employeeservice.service.HrHousingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest()
@Slf4j
class HrControllerTest {

    private HrController hrController;

    @Mock
    private HrHomepageService hrHomepageService;

    @Mock
    private HrEmployeeProfilesService hrEmployeeProfilesService;

    @Mock
    private HrHousingService hrHousingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hrController = new HrController(hrHomepageService, hrEmployeeProfilesService, hrHousingService);
    }

    @Test
    void testFindAllVisaStatus() throws Exception {
        List<VisaStatusResponse> visaStatusResponses = Arrays.asList(new VisaStatusResponse(new Employee(), null));
        when(hrHomepageService.findAllVisaStatusPaginated(1, 10)).thenReturn(visaStatusResponses);
        ResponseEntity<Object> response = hrController.findAllVisaStatus(1, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(visaStatusResponses, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    void testAddEmployee() throws Exception {
        Employee employee = new Employee();
        Mockito.when(hrEmployeeProfilesService.saveEmployee(employee)).thenReturn(employee);
        ResponseEntity<Object> response = hrController.addEmployee(employee);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(employee, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    void testFindAllEmployeesSummaries() throws Exception {
        List<EmployeeSummary> employeeSummaries = Arrays.asList(new EmployeeSummary(new Employee()));
        when(hrEmployeeProfilesService.findAllEmployeesSummaries(2, 8)).thenReturn(employeeSummaries);
        ResponseEntity<Object> response = hrController.findAllEmployeesSummaries(2, 8);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Page 2 of all employees' summary.", ((HashMap<String, Object>)response.getBody()).get("message"));
        assertEquals(employeeSummaries, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    void testFindEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("名字");
        employee.setLastName("姓氏");
        employee.setEmail("xingming@hao123.com");
        employee.setVisaStatuses(Collections.emptyList());
        when(hrEmployeeProfilesService.findEmployeeById(1)).thenReturn(employee);
        ResponseEntity<Object> response = hrController.findEmployeeById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, ((HashMap<String, Object>)response.getBody()).get("data"));
    }
    @Test
    void testFindEmployeeProfileByEmail() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("11@11.com");
        employee.setFirstName("1");
        employee.setLastName("1");
        employee.setMiddleName("1");
        employee.setPreferredName("1");
        when(hrEmployeeProfilesService.findEmployeeProfileByEmail("11@11.com"))
                .thenReturn(new EmployeeProfile(employee));
        ResponseEntity<Object> response = hrController.findEmployeeProfileByEmail("11@11.com");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof HashMap);
        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertEquals("Found the employee profile.", responseBody.get("message"));
        assertEquals(HttpStatus.OK.value(), responseBody.get("status"));
        assertTrue(responseBody.containsKey("data"));
        assertTrue(responseBody.get("data") instanceof EmployeeProfile);
        EmployeeProfile employeeProfile = (EmployeeProfile) responseBody.get("data");
        assertEquals(employee.getEmail(), employeeProfile.getName().getEmail());
        assertEquals(employee.getFirstName(), employeeProfile.getName().getFirstName());
        assertEquals(employee.getLastName(), employeeProfile.getName().getLastName());
        assertEquals(employee.getMiddleName(), employeeProfile.getName().getMiddleName());
        assertEquals(employee.getPreferredName(), employeeProfile.getName().getPreferredName());
    }

    @Test
    void testFindEmployeeProfilesFilteredOnEmail() throws Exception {
        List<EmployeeProfile> employeeProfiles = Arrays.asList(new EmployeeProfile(new Employee()));
        when(hrEmployeeProfilesService.findEmployeeProfilesFilteredOnEmail("test@example.com")).thenReturn(employeeProfiles);
        ResponseEntity<Object> response = hrController.findEmployeeProfilesFilteredOnEmail("test@example.com");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeProfiles, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

    @Test
    void testFindEmployeeProfilesFilteredOnName() {
        List<EmployeeProfile> employeesProfiles = Arrays.asList(
                new EmployeeProfile(new Employee("ming1", "xing1", "x1m1@example.com", 1)),
                new EmployeeProfile(new Employee("ming2", "xing2", "x2m2@example.com", 1))
        );
        when(hrEmployeeProfilesService.findEmployeeProfilesFilteredOnName("xing"))
                .thenReturn(employeesProfiles);
        List<EmployeeProfile> employeeProfiles = hrEmployeeProfilesService.findEmployeeProfilesFilteredOnName("xing");
        assertEquals(2, employeeProfiles.size());
        assertEquals("ming1", employeeProfiles.get(0).getName().getFirstName());
        assertEquals("xing1", employeeProfiles.get(0).getName().getLastName());
        assertEquals("ming2", employeeProfiles.get(1).getName().getFirstName());
        assertEquals("xing2", employeeProfiles.get(1).getName().getLastName());
    }

    @Test
    public void testFindEmployeeSummariesByHouseId() throws Exception {
        List<EmployeeSummary> employeeSummaries = Arrays.asList(new EmployeeSummary(), new EmployeeSummary());
        when(hrHousingService.findEmployeeSummariesByHouseId(1)).thenReturn(employeeSummaries);
        ResponseEntity<Object> response = hrController.findEmployeeSummariesByHouseId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeSummaries, ((HashMap<String, Object>)response.getBody()).get("data"));
    }

}
