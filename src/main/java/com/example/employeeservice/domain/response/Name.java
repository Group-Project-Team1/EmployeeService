package com.example.employeeservice.domain.response;

import com.example.employeeservice.domain.entity.Employee;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Name {
    private String firstName;
    private String lastName;
    private String middleName;
    private String preferredName;

    private String profilePicturePath;

    private String email;
    private String ssn;
    private LocalDate dob;
    private String gender;

    public Name(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.middleName = employee.getMiddleName();
        this.preferredName = employee.getPreferredName();

        this.email= employee.getEmail();
        this.ssn = employee.getSsn();
        this.dob = employee.getDob();
        this.gender = employee.getGender();
    }
}
