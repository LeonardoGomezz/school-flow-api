package com.schoolflow.School_flow_api.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StudentAttendanceDTO {
    private Long studentId;
    private String studentName;
    private List<CourseAttendanceDTO> courseAttendance;
}
