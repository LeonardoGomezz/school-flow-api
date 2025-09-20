package com.schoolflow.School_flow_api.dto;

import com.schoolflow.School_flow_api.entities.AttendanceStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AttendanceDTO {

    private Long id;

    private LocalDate date;

    private AttendanceStatus status;

    private Long studentId;

    private String studentName;

    private Long courseId;

    private String courseName;

}
