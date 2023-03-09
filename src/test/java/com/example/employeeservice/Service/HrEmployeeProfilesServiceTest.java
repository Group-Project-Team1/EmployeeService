package com.example.employeeservice.Service;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.repository.PagingAndSortingRepo;
import com.example.employeeservice.service.HrEmployeeProfilesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HrEmployeeProfilesServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private PagingAndSortingRepo pagingAndSortingRepo;

    @InjectMocks
    private HrEmployeeProfilesService hrEmployeeProfilesService;

    @BeforeEach
    public void setUp() {
        hrEmployeeProfilesService = new HrEmployeeProfilesService();
        hrEmployeeProfilesService.employeeRepo = employeeRepo;
        hrEmployeeProfilesService.pagingAndSortingRepo = pagingAndSortingRepo;
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        when(employeeRepo.save(employee)).thenReturn(employee);

        hrEmployeeProfilesService.saveEmployee(employee);

        assertThat(employee.getId()).isNotNull();
    }

    @Test
    public void testFindAllEmployeesSummaries_withNullInputs() {
        assertThatThrownBy(() -> hrEmployeeProfilesService.findAllEmployeesSummaries(null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Please input the page number and itemsPerPage!");
    }
    @Test
    public void testFindAllEmployeesSummaries_withZeroPage() {
        assertThatThrownBy(() -> hrEmployeeProfilesService.findAllEmployeesSummaries(0, 10))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("The page number should be at least 1!");
    }
}

