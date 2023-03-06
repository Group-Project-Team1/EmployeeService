package com.example.employeeservice.repository;

import com.example.employeeservice.domain.entity.VisaStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VisaStatusRepo extends MongoRepository<VisaStatus, Integer> {
    VisaStatus findVisaStatusById(Integer id);

    void deleteById(Integer id);
}
