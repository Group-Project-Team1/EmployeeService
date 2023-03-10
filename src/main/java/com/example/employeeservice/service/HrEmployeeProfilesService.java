package com.example.employeeservice.service;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.domain.response.EmployeeSummary;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.repository.PagingAndSortingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HrEmployeeProfilesService {

    @Autowired
    public EmployeeRepo employeeRepo;

    @Autowired
    public PagingAndSortingRepo pagingAndSortingRepo;

    public Employee saveEmployee(Employee employee) {
        employeeRepo.save(employee);
        return employee;
    }

    public List<EmployeeSummary> findAllEmployeesSummaries(Integer page, Integer itemsPerPage) {
        if (page == null || itemsPerPage == null) {
            throw new NullPointerException("Please input the page number and itemsPerPage!");
        }
        if (page <= 0) {
            throw new ArithmeticException("The page number should be at least 1!");
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "lastName");
        Pageable pageable = PageRequest.of(page - 1, itemsPerPage, sort);
        List<Employee> employees = pagingAndSortingRepo.findAll(pageable).getContent();
        return employees.stream().sorted((a, b) -> a.getLastName().compareTo(b.getLastName())).map(e -> new EmployeeSummary(e)).collect(Collectors.toList());
    }

    public EmployeeProfile findEmployeeProfileByEmail(String email) {
        Employee employee = employeeRepo.findEmployeeByEmail(email);
        if (employee == null) {
            throw new NullPointerException("The user is not existing");
        }
        return new EmployeeProfile(employee);
    }

    public List<EmployeeProfile> findEmployeeProfilesFilteredOnEmail(String emailSeg) {
        return employeeRepo.findByEmailContains(emailSeg)
                .stream().map(e -> new EmployeeProfile(e)).collect(Collectors.toList());
    }

    public List<EmployeeProfile> findEmployeeProfilesFilteredOnName(String nameSeg) {
        return employeeRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrPreferredNameContainingIgnoreCase(nameSeg, nameSeg, nameSeg, nameSeg)
                .stream().map(e -> new EmployeeProfile(e)).collect(Collectors.toList());
    }


    public Employee findEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new NullPointerException("The user is not existing");
        }
        return employeeOptional.get();
    }
}
