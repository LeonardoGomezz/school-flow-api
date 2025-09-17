package com.schoolflow.School_flow_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrolledStudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
}
