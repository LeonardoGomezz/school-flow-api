package com.schoolflow.School_flow_api.dto.attendance;

import com.schoolflow.School_flow_api.entities.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AttendanceItemDTO {
    private Long id;
    private LocalDate date;
    private AttendanceStatus status;
}
