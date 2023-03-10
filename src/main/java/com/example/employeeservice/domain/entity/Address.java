package com.example.employeeservice.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Address {

    @ToString.Exclude
    private Integer id;
    private String addressLine1;
    private String addressLine2;
    private String City;
    private String State;
    private String zipCode;

}
