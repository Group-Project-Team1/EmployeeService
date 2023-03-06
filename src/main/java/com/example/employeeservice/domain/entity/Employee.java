package com.example.employeeservice.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
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
    private Date dob;
    private Date startDate;
    private Date endDate;
    private String driverLicense;
    private Date driverLicenseExpiration;
    private Integer houseId;
    private List<Contact> contacts;
    private List<Address> addresses;
    private List<VisaStatus> visaStatuses;
    private List<PersonalDocument> personalDocuments;


}
