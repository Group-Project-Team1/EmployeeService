package com.example.employeeservice.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "visaStatus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VisaStatus {
    private Integer id;
    private String visaType;
    private Boolean activeFlag;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate lastModificationDate;
    @ToString.Exclude
    private Employee employee;
}
