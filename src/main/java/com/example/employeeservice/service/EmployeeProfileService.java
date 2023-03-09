package com.example.employeeservice.service;

import com.example.employeeservice.exception.BadInputException;
import com.example.employeeservice.exception.CannotAccessOtherUsersDataException;
import com.example.employeeservice.exception.WrongDateFormatException;
import com.example.employeeservice.domain.entity.*;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmployeeProfileService {

    private EmployeeRepo employeeRepo;

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

    public EmployeeProfile updateProfile(Integer id, String key, String val) {
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new NullPointerException("The user is not existing");
        }
        Employee employee = employeeOptional.get();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }
        key = key.replace(" ", "");
        key = key.toLowerCase();
        if (key.equals("firstname")) {
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
        employee.getPersonalDocuments().add(personalDocument);
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
}
