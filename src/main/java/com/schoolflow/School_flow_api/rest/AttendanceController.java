package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.AttendanceDTO;
import com.schoolflow.School_flow_api.services.interfaces.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAtendances(){
        return ResponseEntity.ok(this.attendanceService.getAllAttendances());
    }

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

    @PostMapping
    public ResponseEntity<AttendanceDTO> takeAttendance(@RequestBody AttendanceDTO attendanceDTO){
        return ResponseEntity.ok(this.attendanceService.takeAttendance(attendanceDTO));
    }
}
