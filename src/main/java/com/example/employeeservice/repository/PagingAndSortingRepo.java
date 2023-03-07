package com.example.employeeservice.repository;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.VisaStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagingAndSortingRepo extends PagingAndSortingRepository<Employee, Integer> {


}
