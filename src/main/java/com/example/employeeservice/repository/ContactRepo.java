package com.example.employeeservice.repository;

import com.example.employeeservice.domain.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends MongoRepository<Contact, Integer> {

    Contact findContactById(Integer id);

    void deleteById(Integer id);
}
