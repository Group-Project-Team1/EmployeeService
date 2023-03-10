package com.example.employeeservice.Service;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.response.EmployeeSummary;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.service.HrHousingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class HrHousingServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private HrHousingService hrHousingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testFindEmployeeSummariesByHouseId() {
//        // arrange
//        int houseId = 1;
//        List<Employee> employees = Arrays.asList(
//                new Employee("JJ", "DD", "jd@qq.com", houseId),
//                new Employee("dd", "jj", "dj@gmail.com", houseId)
//        );
//        List<EmployeeSummary> expectedSummaries = Arrays.asList(
//                new EmployeeSummary(employees.get(0)),
//                new EmployeeSummary(employees.get(1))
//        );
//        when(employeeRepo.findEmployeesByHouseId(houseId)).thenReturn(employees);
//
//        // act
//        List<EmployeeSummary> actualSummaries = hrHousingService.findEmployeeSummariesByHouseId(houseId);
//
//        // assert
//        assertEquals(expectedSummaries, actualSummaries);
//        verify(employeeRepo, times(1)).findEmployeesByHouseId(houseId);
//    }
}
