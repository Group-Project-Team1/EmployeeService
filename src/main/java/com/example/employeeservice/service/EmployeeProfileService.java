package com.example.employeeservice.service;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProfileService {

    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeProfileService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
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
