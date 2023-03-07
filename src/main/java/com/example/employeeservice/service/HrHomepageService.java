package com.example.employeeservice.service;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.VisaStatus;
import com.example.employeeservice.domain.response.VisaStatusResponse;
import com.example.employeeservice.repository.EmployeeRepo;
import org.hibernate.hql.internal.ast.tree.AggregateNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HrHomepageService {

    @Autowired
    private EmployeeRepo employeeRepo;


    public List<VisaStatusResponse> findAllVisaStatusPaginated(int page, int size) {
        List<Employee> employees = employeeRepo.findAll();
        List<VisaStatusResponse> visaStatusResponses = employees.stream()
                .map(e -> e.getVisaStatuses().stream().filter(v -> v.getActiveFlag()).map(v -> new VisaStatusResponse(e, v)).collect(Collectors.toList()))
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());
        int n = visaStatusResponses.size();
        int totalPages = (int) Math.floor((double)n /(double)size);
        if (page > totalPages) {
            throw new ArithmeticException("Exceed! Try smaller number of page.");
        }
        if (size > n) {
            throw new ArithmeticException("Exceed! Try smaller number of size.");
        }
        return visaStatusResponses.subList(size * (page - 1), Math.min(size * page, n));
    }


}
