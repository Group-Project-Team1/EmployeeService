package com.example.employeeservice.domain.response;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.VisaStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employment {
    private String visaTitle;
    private LocalDate startDate;
    private LocalDate endDate;

    public Employment(Employee employee) {
        Optional<VisaStatus> visaStatusOptional = employee.getVisaStatuses().stream()
                .filter(v -> v.getActiveFlag())
                .sorted((a, b) -> b.getStartDate().compareTo(a.getStartDate()))
                .findFirst();
        this.visaTitle = visaStatusOptional.isPresent() ? visaStatusOptional.get().getVisaType() : "N/A";
//        this.visaTitle = employee.getVisaStatuses().stream()
//                .filter(v -> v.getActiveFlag())
//                .sorted((a, b) -> b.getStartDate().compareTo(a.getStartDate()))
//                .findFirst().get().getVisaType();
        this.startDate = employee.getStartDate();
        this.endDate = employee.getEndDate();
    }
}
