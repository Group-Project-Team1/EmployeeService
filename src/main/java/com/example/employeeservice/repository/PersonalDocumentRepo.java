package com.example.employeeservice.repository;

import com.example.employeeservice.domain.entity.PersonalDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonalDocumentRepo extends MongoRepository<PersonalDocument, Integer> {

    PersonalDocument findPersonalDocumentById(Integer id);

    void deleteById(Integer id);
}
