package com.example.employeeservice.service;

import com.example.employeeservice.domain.Contact;
import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.domain.PersonalDocument;
import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.repository.ContactRepo;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.repository.PersonalDocumentRepo;
import com.example.employeeservice.repository.VisaStatusRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    private EmployeeRepo employeeRepo;

    private ContactRepo contactRepo;

    private VisaStatusRepo visaStatusRepo;
    private PersonalDocumentRepo personalDocumentRepo;

    public EmployeeService(EmployeeRepo employeeRepo, ContactRepo contactRepo) {
        this.employeeRepo = employeeRepo;
        this.contactRepo = contactRepo;
    }

    public void saveEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public void saveContact(Contact contact) {
        contactRepo.save(contact);
    }

    public void saveVisaStatus(VisaStatus visaStatus) {
        visaStatusRepo.save(visaStatus);
    }

    public void savePersonalDocument(PersonalDocument personalDocument) {
        personalDocumentRepo.save(personalDocument);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }


    public Employee findEmployeeById(int id) {
        return employeeRepo.findEmployeeById(id);
    }
}
