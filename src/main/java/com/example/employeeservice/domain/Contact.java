package com.example.employeeservice.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private Integer id;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String AlternatePhone;
    private String email;
    private String Relationship;
    private String type;
}
