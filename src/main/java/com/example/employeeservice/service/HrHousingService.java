package com.example.employeeservice.service;

import com.example.employeeservice.domain.response.EmployeeSummary;
import com.example.employeeservice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HrHousingService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<EmployeeSummary> findEmployeeSummariesByHouseId(Integer houseId) {
        return employeeRepo.findEmployeesByHouseId(houseId)
                .stream().map(e -> new EmployeeSummary(e)).collect(Collectors.toList());
    }
}
