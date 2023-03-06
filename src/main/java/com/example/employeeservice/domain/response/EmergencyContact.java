package com.example.employeeservice.domain.response;

import com.example.employeeservice.domain.entity.Contact;
import com.example.employeeservice.domain.entity.Employee;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmergencyContact {
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private String relationship;
    public EmergencyContact(Contact contact) {
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();

        this.phone = contact.getCellPhone();
        this.email = contact.getEmail();
        this.relationship = contact.getRelationship();
    }
}
