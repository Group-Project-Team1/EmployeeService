package com.example.employeeservice.service;

import com.example.employeeservice.Exception.CannotAccessOtherUsersDataException;
import com.example.employeeservice.domain.entity.*;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProfileService {

    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeProfileService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    //这里开始
    public EmployeeProfile updateEmployee(Employee employee) {
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userId);
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot view the profile of other employee.");
        }
        employeeRepo.save(employee);
        return new EmployeeProfile(employee);
    }

    public EmployeeProfile findEmployeeProfileById(Integer id) {
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeRepo.findEmployeeById(id);
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot view the profile of other employee.");
        }
        return new EmployeeProfile(employee);
    }

    public EmployeeProfile updateProfile(Integer id, String key, String val) {
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeRepo.findById(id).get();
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
            employee.setDriverLicenseExpiration(LocalDate.parse(val));
        }
        else if (key.equals("driverlicense")) {
            employee.setDriverLicense(val);
        }
        else if (key.equals("driverlicenseexpiration")) {
            employee.setDriverLicenseExpiration(LocalDate.parse(val));
        }
        employeeRepo.save(employee);
        return new EmployeeProfile(employee);
    }

    public EmployeeProfile updateProfile(String key, String val) {
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeRepo.findEmployeeByUserId(userId);
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
            employee.setDriverLicenseExpiration(LocalDate.parse(val));
        }
        else if (key.equals("driverlicense")) {
            employee.setDriverLicense(val);
        }
        else if (key.equals("driverlicenseexpiration")) {
            employee.setDriverLicenseExpiration(LocalDate.parse(val));
        }
        employeeRepo.save(employee);
        return new EmployeeProfile(employee);
    }

    public void addContact(Integer id, Contact contact) {
        Employee employee = employeeRepo.findEmployeeById(id);
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }
        employee.getContacts().add(contact);
        employeeRepo.save(employee);
    }

    public void addAddress(Integer id, Address address) {
        Employee employee = employeeRepo.findById(id).get();
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }
        employee.getAddresses().add(address);
        employeeRepo.save(employee);
    }

    public void addVisaStatus(Integer id, VisaStatus visaStatus) {
        Employee employee = employeeRepo.findById(id).get();
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }
        employee.getVisaStatuses().add(visaStatus);
        employeeRepo.save(employee);
    }

    public void addPersonalDocument(Integer id, PersonalDocument personalDocument) {
        Employee employee = employeeRepo.findById(id).get();
        int userId = (int)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (employee.getUserId() != userId) {
            throw new CannotAccessOtherUsersDataException("You cannot update the profile of other employee.");
        }
        employee.getPersonalDocuments().add(personalDocument);
        employeeRepo.save(employee);
    }

    // 这里结束






}
