package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.attendance.AttendanceDTO;
import com.schoolflow.School_flow_api.dto.attendance.StudentAttendanceDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AttendanceService {

    List<AttendanceDTO> getAllAttendances();

    List<AttendanceDTO> getAttendanceByCourse(Long courseId, LocalDate date, LocalDate startDate, LocalDate endDate);

    Map<Long, List<AttendanceDTO>> getAttendanceByStudent(Long studentId, LocalDate startDate, LocalDate endDate);

    AttendanceDTO takeAttendance(AttendanceDTO attendanceDTO);

    StudentAttendanceDTO getAttendanceByStudentGroupedByCourses(Long studentId);
}
