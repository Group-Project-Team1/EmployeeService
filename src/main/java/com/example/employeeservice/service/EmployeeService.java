package com.example.employeeservice.service;

import com.example.employeeservice.domain.entity.Contact;
import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.PersonalDocument;
import com.example.employeeservice.domain.entity.VisaStatus;
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

    public List<Employee> getAllEmployeesPaginated(int page, int size) {
        List<Employee> employees = employeeRepo.findAll();
        int n = employees.size();
        int totalPages = (int) Math.floor((double)n /(double)size);
        if (page > totalPages) {
            throw new ArithmeticException("Exceed! Try smaller number of page.");
        }
        if (size > n) {
            throw new ArithmeticException("Exceed! Try smaller number of size.");
        }
        return employees.subList(size * (page - 1), Math.min(size * page, n));
    }
}
