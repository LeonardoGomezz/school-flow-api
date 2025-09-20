package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.AttendanceDTO;
import com.schoolflow.School_flow_api.entities.Attendance;
import com.schoolflow.School_flow_api.entities.Course;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.repositories.AttendanceRepository;
import com.schoolflow.School_flow_api.repositories.CourseRepository;
import com.schoolflow.School_flow_api.repositories.StudentRepository;
import com.schoolflow.School_flow_api.services.interfaces.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServiceImpls implements AttendanceService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<AttendanceDTO> getAllAttendances() {
        return this.attendanceRepository.findAll()
                .stream().map(
                        at -> AttendanceDTO.builder()
                                .id(at.getId())
                                .date(at.getDate())
                                .status(at.getStatus())
                                .studentId(at.getStudent().getId())
                                .studentName(at.getStudent().getFirstName()+" "+at.getStudent().getLastName())
                                .courseId(at.getCourse().getId())
                                .courseName(at.getCourse().getName())
                                .build()).toList();
    }

    @Override
    public List<AttendanceDTO> getAttendanceByCourse(Long courseId, LocalDate date, LocalDate startDate, LocalDate endDate) {
        List<Attendance> attendances;

        if (date != null) {
            attendances = attendanceRepository.findByCourseIdAndDate(courseId, date);
        } else if (startDate != null && endDate != null) {
            attendances = attendanceRepository.findByCourseIdAndDateBetween(courseId, startDate, endDate);
        } else {
            attendances = attendanceRepository.findByCourseId(courseId);
        }

        return attendances.stream().map(
                at -> AttendanceDTO.builder()
                        .id(at.getId())
                        .date(at.getDate())
                        .status(at.getStatus())
                        .studentId(at.getStudent().getId())
                        .studentName(at.getStudent().getFirstName() + " " + at.getStudent().getLastName())
                        .courseId(at.getCourse().getId())
                        .courseName(at.getCourse().getName())
                        .build()).toList();
    }

    @Override
    public AttendanceDTO takeAttendance(AttendanceDTO attendanceDTO) {
        Course course = this.courseRepository.findById(attendanceDTO.getCourseId()).orElse(null);
        Student student = this.studentRepository.findById(attendanceDTO.getStudentId()).orElse(null);

        if (course == null){
            throw new EntityNotFoundException("Course not found");
        }

        if (student == null){
            throw new EntityNotFoundException("Student not found");
        }

        Attendance attendance = new Attendance();
        attendance.setDate(attendanceDTO.getDate());
        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setCourse(course);
        attendance.setStudent(student);

        attendance = this.attendanceRepository.save(attendance);

        return AttendanceDTO.builder()
                .id(attendance.getId())
                .date(attendance.getDate())
                .status(attendance.getStatus())
                .studentId(attendance.getStudent().getId())
                .studentName(attendance.getStudent().getFirstName()+" "+attendance.getStudent().getLastName())
                .courseId(attendance.getCourse().getId())
                .courseName(attendance.getCourse().getName())
                .build();
    }
}
