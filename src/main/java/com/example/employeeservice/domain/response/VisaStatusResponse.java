package com.example.employeeservice.domain.response;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.VisaStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VisaStatusResponse {
    private String name;
    private String workAuthorization;
    private LocalDate expirationDate;
    private Integer daysLeft;

    public VisaStatusResponse(Employee employee, VisaStatus visaStatus) {
        if (employee == null || visaStatus == null) return;
        this.name = employee.getFirstName() + " " + (employee.getMiddleName() + employee.getMiddleName() == null ? "" : " ") + employee.getLastName();
        this.workAuthorization = visaStatus.getVisaType();
        this.expirationDate = visaStatus.getEndDate();
        this.daysLeft = (int) ChronoUnit.DAYS.between(LocalDate.now(), visaStatus.getEndDate());
    }
}
