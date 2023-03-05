package com.example.employeeservice.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
    private String SSN;
    private Date DOB;
    private Date startDate;
    private Date endDate;
    private DriverLicense DriverLicense;
    private Date driverLicenseExpiration;
    private String houseId;
    private Contact contact;
    private Address address;
    private VisaStatus visaStatus;
    private PersonalDocument personalDocument;


}
