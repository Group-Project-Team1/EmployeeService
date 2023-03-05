package com.example.employeeservice.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Date startDate;
    private Date endDate;
    private Date lastModificationDate;
}
