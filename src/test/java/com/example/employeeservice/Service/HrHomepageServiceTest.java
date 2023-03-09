package com.example.employeeservice.Service;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.VisaStatus;
import com.example.employeeservice.domain.response.VisaStatusResponse;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.service.HrHomepageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HrHomepageServiceTest {

    @InjectMocks
    private HrHomepageService hrHomepageService;

    @Mock
    private EmployeeRepo employeeRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllVisaStatusPaginated() {
        // Create test data
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstName("Pi");
        employee1.setLastName("Liu");
        VisaStatus visaStatus1 = new VisaStatus();
        visaStatus1.setId(1);
        visaStatus1.setActiveFlag(true);
        visaStatus1.setEndDate(LocalDate.of(2022, 6, 30));
        visaStatus1.setVisaType("OPT");
        visaStatus1.setEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setFirstName("Feng");
        employee2.setLastName("Lei");
        VisaStatus visaStatus2 = new VisaStatus();
        visaStatus2.setId(2);
        visaStatus2.setActiveFlag(true);
        visaStatus2.setEndDate(LocalDate.of(2023, 8, 31));
        visaStatus2.setVisaType("L1");
        visaStatus2.setEmployee(employee2);

        Employee employee3 = new Employee();
        employee3.setId(3);
        employee3.setFirstName("Bb");
        employee3.setLastName("Ss");
        VisaStatus visaStatus3 = new VisaStatus();
        visaStatus3.setId(3);
        visaStatus3.setActiveFlag(false);
        visaStatus3.setEndDate(LocalDate.of(2021, 12, 31));
        visaStatus3.setVisaType("H1B");
        visaStatus3.setEmployee(employee3);

        List<Employee> employees = Arrays.asList(employee1, employee2, employee3);

        // Set up mock behavior for employeeRepo
        when(employeeRepo.findAll()).thenReturn(employees);

        // Call the method being tested
        List<VisaStatusResponse> result = hrHomepageService.findAllVisaStatusPaginated(1, 2);

        // Assert the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(new VisaStatusResponse(employee1, visaStatus1), result.get(0));
        Assertions.assertEquals(new VisaStatusResponse(employee2, visaStatus2), result.get(1));
    }

    @Test
    public void testFindAllVisaStatusPaginatedWithInvalidPageNumber() {
        // Mock the repository call
        when(employeeRepo.findAll()).thenReturn(new ArrayList<>());

        // Call the service method with invalid page number
        Assertions.assertThrows(ArithmeticException.class, () -> hrHomepageService.findAllVisaStatusPaginated(0, 1));
    }

    @Test
    public void testFindAllVisaStatusPaginatedWithInvalidItemsPerPage() {
        // Mock the repository call
        when(employeeRepo.findAll()).thenReturn(new ArrayList<>());

        // Call the service method with invalid items per page
        Assertions.assertThrows(ArithmeticException.class, () -> hrHomepageService.findAllVisaStatusPaginated(1, 0));
    }
}
