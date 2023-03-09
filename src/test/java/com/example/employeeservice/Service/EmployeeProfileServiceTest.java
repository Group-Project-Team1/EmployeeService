package com.example.employeeservice.Service;

import com.example.employeeservice.exception.BadInputException;
import com.example.employeeservice.exception.CannotAccessOtherUsersDataException;
import com.example.employeeservice.domain.entity.*;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.service.EmployeeProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeProfileServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private EmployeeProfileService employeeProfileService;

    private final int userId = 1;
    private final int employeeId = 1;
    private final Employee employee = new Employee();
    private final EmployeeProfile employeeProfile = new EmployeeProfile(employee);

    @BeforeEach
    public void setUp() {
        employee.setUserId(userId);
        employee.setId(employeeId);
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getPrincipal()).thenReturn(userId);
        when(employeeRepo.save(employee)).thenReturn(employee);

        // Act
        EmployeeProfile result = employeeProfileService.updateEmployee(employee);

        // Assert
        assertEquals(employeeProfile, result);
        verify(employeeRepo, times(1)).save(employee);
    }

    @Test
    public void testUpdateEmployeeThrowsCannotAccessOtherUsersDataException() {
        // Arrange
        employee.setUserId(userId + 1);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getPrincipal()).thenReturn(userId);

        // Act & Assert
        assertThrows(CannotAccessOtherUsersDataException.class, () -> employeeProfileService.updateEmployee(employee));
        verify(employeeRepo, never()).save(employee);
    }

    @Test
    public void testFindEmployeeProfileById() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getPrincipal()).thenReturn(userId);
        when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employee));

        // Act
        EmployeeProfile result = employeeProfileService.findEmployeeProfileById(String.valueOf(employeeId));

        // Assert
        assertEquals(employeeProfile, result);
        verify(employeeRepo, times(1)).findById(employeeId);
    }

    @Test
    public void testFindEmployeeProfileByIdThrowsBadInputException() {
        // Arrange
        String id = "abc";

        // Act & Assert
        assertThrows(BadInputException.class, () -> employeeProfileService.findEmployeeProfileById(id));
        verify(employeeRepo, never()).findById(anyInt());
    }
}
