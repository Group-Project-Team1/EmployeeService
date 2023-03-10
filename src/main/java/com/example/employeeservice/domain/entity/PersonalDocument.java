package com.example.employeeservice.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "personalDocument")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PersonalDocument {
    private Integer id;
    private String path;
    private String title;
    private String comment;
    private Date createDate;

}
