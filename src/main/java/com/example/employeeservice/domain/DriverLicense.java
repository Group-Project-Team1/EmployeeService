package com.example.employeeservice.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "driverLicense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DriverLicense {
    private Integer id;
    private String path;

}
