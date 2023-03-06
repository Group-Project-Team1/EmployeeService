package com.example.employeeservice.domain.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ContactInfo {

    private String cellPhone;
    private String workPhone;
}
