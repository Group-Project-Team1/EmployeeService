package com.example.employeeservice.service;

import com.example.employeeservice.exception.BadInputException;
import com.example.employeeservice.exception.CannotAccessOtherUsersDataException;
import com.example.employeeservice.exception.DuplicateIdException;
import com.example.employeeservice.exception.WrongDateFormatException;
import com.example.employeeservice.domain.entity.*;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.repository.EmployeeRepo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeProfileService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeProfileService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeProfile updateEmployee(Employee employee) {
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot view the profile of other employee.");
        }
        employeeRepo.save(employee);
        return new EmployeeProfile(employee);
    }

    public EmployeeProfile findEmployeeProfileById(String id) {
        try {
            Integer.parseInt(id);
        } catch (Exception e) {
            throw new BadInputException("Please input an integer!");
        }
        int employeeId = Integer.valueOf(id);
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new NullPointerException("The user is not existing");
        }
        Employee employee = employeeOptional.get();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot view the profile of other employee.");
        }
        return new EmployeeProfile(employee);
    }

    public EmployeeProfile updateProfile(Integer userId, String key, String val) {
        Employee employee = employeeRepo.findEmployeeByUserId(userId);
        if (employee == null) {
            throw new NullPointerException("The user is not existing");
        }
        if ((int)SecurityContextHolder.getContext().getAuthentication().getPrincipal() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }
        key = key.replace(" ", "");
        key = key.toLowerCase();
        if (key.equals("id")) {
            try {
                Integer.parseInt(val);
            } catch (Exception e) {
                throw new BadInputException("You should input an integer for id!");
            }
            Integer employeeId = Integer.valueOf(val);
            if (employee.getId() != employeeId) {
                List<Employee> employees = employeeRepo.findAll();
                Optional<Employee> employeeOptional = employees.stream().filter(e -> e.getId() == employeeId).findAny();
                if (employeeOptional.isPresent()) {
                    throw new DuplicateIdException("The id is already exist!");
                }
                employee.setId(Integer.valueOf(val));
            }
        }
        else if (key.equals("firstname")) {
            employee.setFirstName(val);
        }
        else if (key.equals("lastname")) {
            employee.setLastName(val);
        }
        else if (key.equals("middlename")) {
            employee.setMiddleName(val);
        }
        else if (key.equals("preferredname")) {
            employee.setPreferredName(val);
        }
        else if (key.equals("email")) {
            employee.setEmail(val);
        }
        else if (key.equals("cellphone")) {
            employee.setCellPhone(val);
        }
        else if (key.equals("alternatephone")) {
            employee.setAlternatePhone(val);
        }
        else if (key.equals("gender")) {
            employee.setGender(val);
        }
        else if (key.equals("ssn")) {
            employee.setSsn(val);
        }
        else if (key.equals("dob")) {
            try {
                employee.setDob(LocalDate.parse(val));
            } catch (Exception e) {
                throw new WrongDateFormatException("Please input the date as 'yyyy-MM-dd' format!");
            }
        }
        else if (key.equals("driverlicense")) {
            employee.setDriverLicense(val);
        }
        else if (key.equals("driverlicenseexpiration")) {
            try {
                employee.setDriverLicenseExpiration(LocalDate.parse(val));
            } catch (Exception e) {
                throw new WrongDateFormatException("Please input the date as 'yyyy-MM-dd' format!");
            }
        }
        employeeRepo.save(employee);
        return new EmployeeProfile(employee);
    }


    public void addContact(Integer id, Contact contact) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new NullPointerException("The user is not existing");
        }
        Employee employee = employeeOptional.get();
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }
        employee.getContacts().add(contact);
        employeeRepo.save(employee);
    }

    public void addAddress(Integer id, Address address) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new NullPointerException("The user is not existing");
        }
        Employee employee = employeeOptional.get();
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }
        employee.getAddresses().add(address);
        employeeRepo.save(employee);
    }

    public void addVisaStatus(Integer id, VisaStatus visaStatus) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new NullPointerException("The user is not existing");
        }
        Employee employee = employeeOptional.get();
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }
        employee.getVisaStatuses().add(visaStatus);
        employeeRepo.save(employee);
    }

    public void addPersonalDocument(Integer id, PersonalDocument personalDocument) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new NullPointerException("The user is not existing");
        }
        Employee employee = employeeOptional.get();
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }

        List<PersonalDocument> personalDocuments = employee.getPersonalDocuments();
        for(PersonalDocument p : personalDocuments){
            if(p.getTitle().equals(personalDocument.getTitle())){
                personalDocuments.remove(p);
            }
        }
        personalDocuments.add(personalDocument);
        employee.setPersonalDocuments(personalDocuments);
        employeeRepo.save(employee);
    }

    public Employee findEmployeeById (Integer id){
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new NullPointerException("The user is not existing");
        }
        Employee employee = employeeOptional.get();
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot access the profile of other employee.");
        }
        return employee;
    }

    public Employee addEmployee(Employee employee) {
        System.out.println(employee);
        employeeRepo.save(employee);
        return employee;
    }

    public Integer generateEmployeeId() {
        List<Employee> employees = employeeRepo.findAll();
        if (employees == null || employees.size() == 0) {
            return 1;
        }
        Collections.sort(employees, (a, b) -> a.getId() - b.getId());
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() != i + 1) {
                return i + 1;
            }
        }
        return employees.size();
    }
    public Employee findEmployeeByEmployeeId(Integer employeeId) {
//        Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);
//        if (!employeeOptional.isPresent()) {
//            throw new NullPointerException("The employee id is not existing.");
//        }
//        System.out.println(employeeOptional.get().getId());
//        System.out.println(employeeOptional.get().getUserId());
//        return employeeOptional.get();
        Employee employee = employeeRepo.findEmployeeById(employeeId);
//        System.out.println(employeeId);
//        System.out.println(employee.getId());
//        System.out.println(employee.getUserId());
        return employee;
    }
}
