package com.example.employeeservice.domain.response;

import com.example.employeeservice.domain.entity.Employee;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmployeeSummary {
    private String name;  // legal full name
    private String ssn;
    private String workAuthorizationTitle;
    private String phoneNumber;
    private String email;

    public EmployeeSummary(Employee employee) {
        this.name = employee.getFirstName() + " " + (employee.getMiddleName() + employee.getMiddleName() == null ? "" : " ") + employee.getLastName();
        this.ssn = employee.getSsn();
        this.workAuthorizationTitle = employee.getVisaStatuses().get(employee.getVisaStatuses().size() - 1).getVisaType();
        this.phoneNumber = employee.getCellPhone();
        this.email = employee.getEmail();
    }
}
