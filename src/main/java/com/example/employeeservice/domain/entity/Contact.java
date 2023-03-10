package com.example.employeeservice.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Contact {

    @ToString.Exclude
    private Integer id;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String AlternatePhone;
    private String email;
    private String Relationship;
    @ToString.Exclude
    private String type;

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
