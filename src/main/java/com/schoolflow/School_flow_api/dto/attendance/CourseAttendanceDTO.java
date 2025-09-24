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
public class CourseAttendanceDTO {
    private Long courseId;
    private String courseName;
    private List<AttendanceItemDTO> attendance;
}
