package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.attendance.AttendanceDTO;
import com.schoolflow.School_flow_api.dto.attendance.StudentAttendanceDTO;
import com.schoolflow.School_flow_api.services.interfaces.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAtendances(){
        return ResponseEntity.ok(this.attendanceService.getAllAttendances());
    }

    // 1. Consultar asistencias de un curso (por fecha o rango)
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByCourse(
            @PathVariable Long courseId,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        return ResponseEntity.ok(
                attendanceService.getAttendanceByCourse(courseId, date, startDate, endDate)
        );
    }

    // 2. Consultar asistencias de un estudiante (historial)
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<Long, List<AttendanceDTO>>> getAttendanceByStudent(
            @PathVariable Long studentId,
            @RequestParam(required = false)
            LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate){
        return ResponseEntity.ok(
                attendanceService.getAttendanceByStudent(studentId, startDate, endDate)
        );
    }

    // 3. Consultar asistencia de un estudiante agrupadas por curso
    @GetMapping("/student/{studentId}/courses")
    public ResponseEntity<StudentAttendanceDTO> getAttendanceByStudentGroupedByCourses(@PathVariable Long studentId){
        return ResponseEntity.ok(this.attendanceService.getAttendanceByStudentGroupedByCourses(studentId));
    }

    //4. Registrar asistencia
    @PostMapping
    public ResponseEntity<AttendanceDTO> takeAttendance(@RequestBody AttendanceDTO attendanceDTO){
        return ResponseEntity.ok(this.attendanceService.takeAttendance(attendanceDTO));
    }
}
