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
import java.util.stream.Collectors;

@Service
public class HrEmployeeProfilesService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PagingAndSortingRepo pagingAndSortingRepo;

    public void saveEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public List<EmployeeSummary> findAllEmployeesSummaries(int page, int itemsPerPage) {
        Sort sort = Sort.by(Sort.Direction.ASC, "lastName");
        Pageable pageable = PageRequest.of(page, itemsPerPage, sort);
        List<Employee> employees = pagingAndSortingRepo.findAll(pageable).getContent();
        return employees.stream().sorted((a, b) -> a.getLastName().compareTo(b.getLastName())).map(e -> new EmployeeSummary(e)).collect(Collectors.toList());
    }

    public EmployeeProfile findEmployeeProfileById(Integer id) {
        return new EmployeeProfile(employeeRepo.findEmployeeById(id));
    }
    public EmployeeProfile findEmployeeProfileByEmail(String email) {
        return new EmployeeProfile(employeeRepo.findEmployeeByEmail(email));
    }

    public List<EmployeeProfile> findEmployeeProfilesFilteredOnEmail(String emailSeg) {
        return employeeRepo.findByEmailContains(emailSeg)
                .stream().map(e -> new EmployeeProfile(e)).collect(Collectors.toList());
    }

    public List<EmployeeProfile> findEmployeeProfilesFilteredOnName(String nameSeg) {
        return employeeRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrPreferredNameContainingIgnoreCase(nameSeg, nameSeg, nameSeg, nameSeg)
                .stream().map(e -> new EmployeeProfile(e)).collect(Collectors.toList());
    }


}
