package com.example.employeeservice.service;

import com.example.employeeservice.domain.entity.Contact;
import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.PersonalDocument;
import com.example.employeeservice.domain.entity.VisaStatus;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.domain.response.VisaStatusResponse;
import com.example.employeeservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    EmployeeRepo employeeRepo;

    ContactRepo contactRepo;

    PagingAndSortingRepo pagingAndSortingRepo;

    VisaStatusRepo visaStatusRepo;
    PersonalDocumentRepo personalDocumentRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, ContactRepo contactRepo, VisaStatusRepo visaStatusRepo, PersonalDocumentRepo personalDocumentRepo, PagingAndSortingRepo pagingAndSortingRepo) {
        this.employeeRepo = employeeRepo;
        this.contactRepo = contactRepo;
        this.visaStatusRepo = visaStatusRepo;
        this.personalDocumentRepo = personalDocumentRepo;
        this.pagingAndSortingRepo = pagingAndSortingRepo;
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

    public List<Employee> findEmployeesByPage(int page, int itemsPerPage) {
        Sort sort = Sort.by(Sort.Direction.ASC, "lastName");
        Pageable pageable = PageRequest.of(page, itemsPerPage, sort);
        return pagingAndSortingRepo.findAll(pageable).getContent();
    }

    public List<Employee> findEmployeesFilteringEmail(String emailSeg) {
        return employeeRepo.findByEmailContains(emailSeg);
    }

    public List<Employee> findEmployeesFilteringName(String nameSeg) {
        return employeeRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrPreferredNameContainingIgnoreCase(nameSeg, nameSeg, nameSeg, nameSeg);
    }

    public Employee findEmployeeByEmail(String email) {
        return employeeRepo.findEmployeeByEmail(email);
    }

    public List<VisaStatusResponse> findAllVisaStatusPaginated(int page, int size) {
        List<Employee> employees = findAllEmployees();
        List<VisaStatusResponse> visaStatusResponses = employees.stream()
                .map(e -> e.getVisaStatuses().stream().map(v -> new VisaStatusResponse(e, v)).collect(Collectors.toList()))
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
