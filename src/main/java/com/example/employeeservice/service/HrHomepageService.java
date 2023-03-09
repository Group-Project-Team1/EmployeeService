package com.example.employeeservice.service;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.response.VisaStatusResponse;
import com.example.employeeservice.repository.EmployeeRepo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HrHomepageService {

    private final EmployeeRepo employeeRepo;

    public HrHomepageService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<VisaStatusResponse> findAllVisaStatusPaginated(Integer page, Integer size) {
        if (page == null || size == null) {
            throw new NullPointerException("Please input the page number and itemsPerPage!");
        }
        List<Employee> employees = employeeRepo.findAll();
        List<VisaStatusResponse> visaStatusResponses = employees.stream()
                .map(e -> e.getVisaStatuses().stream().filter(v -> v.getActiveFlag()).map(v -> new VisaStatusResponse(e, v)).collect(Collectors.toList()))
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());
        int n = visaStatusResponses.size();
        if (size > n) {
            size = n;
        }
        int totalPages = (int) Math.ceil((double)n /(double)size);
        if (page > totalPages) {
            return new ArrayList<>();
        }
        if (size <= 0) {
            throw new ArithmeticException("There should be at least one item on each page!");
        }
        if (page <= 0) {
            throw new ArithmeticException("The page number should be at least 1!");
        }
        return visaStatusResponses.subList(size * (page - 1), Math.min(size * page, n));
    }
}
