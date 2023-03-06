package com.example.employeeservice.domain.response;

import com.example.employeeservice.domain.entity.Address;
import com.example.employeeservice.domain.entity.Employee;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmployeeProfile {

    private Name name;
    private Address address;
    private ContactInfo contactInfo;
    private Employment employment;
    private EmergencyContact emergencyContact;
    private List<String> documents;

    public EmployeeProfile(Employee employee) {
        this.name = new Name(employee);
        this.address = employee.getAddresses().get(employee.getAddresses().size() - 1);
        this.contactInfo = new ContactInfo(employee.getCellPhone(), employee.getAlternatePhone());
        this.employment = new Employment(employee);
        this.emergencyContact = new EmergencyContact(employee.getContacts().get(employee.getContacts().size() - 1));
        this.documents = employee.getPersonalDocuments().stream().map(d -> d.getPath()).collect(Collectors.toList());
    }

}
