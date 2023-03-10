package com.example.employeeservice.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee {
    @Id
    private Integer id;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String preferredName;
    private String email;
    private String cellPhone;
    private String alternatePhone;
    private String gender;
    private String ssn;
    private LocalDate dob;
    private LocalDate startDate;
    private LocalDate endDate;
    private String driverLicense;
    private LocalDate driverLicenseExpiration;
    private Integer houseId;
    private List<Contact> contacts = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<VisaStatus> visaStatuses = new ArrayList<>();
    private List<PersonalDocument> personalDocuments = new ArrayList<>();

    public Employee(Integer userId) {
        this.userId = userId;
    }

    public Employee(String firstName, String lastName, String email, Integer houseId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.houseId = houseId;
    }

}
