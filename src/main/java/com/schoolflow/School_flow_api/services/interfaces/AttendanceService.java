package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.AttendanceDTO;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    List<AttendanceDTO> getAllAttendances();

    List<AttendanceDTO> getAttendanceByCourse(Long courseId, LocalDate date, LocalDate startDate, LocalDate endDate);

    AttendanceDTO takeAttendance(AttendanceDTO attendanceDTO);

}
