package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.attendance.AttendanceDTO;
import com.schoolflow.School_flow_api.dto.attendance.AttendanceItemDTO;
import com.schoolflow.School_flow_api.dto.attendance.CourseAttendanceDTO;
import com.schoolflow.School_flow_api.dto.attendance.StudentAttendanceDTO;
import com.schoolflow.School_flow_api.entities.Attendance;
import com.schoolflow.School_flow_api.entities.Course;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.repositories.AttendanceRepository;
import com.schoolflow.School_flow_api.repositories.CourseRepository;
import com.schoolflow.School_flow_api.repositories.StudentRepository;
import com.schoolflow.School_flow_api.services.interfaces.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<Long, List<AttendanceDTO>> getAttendanceByStudent(Long studentId, LocalDate startDate, LocalDate endDate) {
        List<Attendance> attendances;

        if (startDate != null && endDate != null){
            attendances = this.attendanceRepository.findByStudentIdAndDateBetween(studentId, startDate, endDate);
        }else if (startDate != null){
            attendances = attendanceRepository.findByStudentIdAndDate(studentId, startDate);
        }else{
            attendances = attendanceRepository.findByStudentId(studentId);
        }

        Map<Long, List<AttendanceDTO>> attendanceMap = new HashMap<>();
        attendances.forEach(at ->{
            if (!attendanceMap.containsKey(at.getCourse().getId())){
                attendanceMap.put(at.getCourse().getId(), new ArrayList<>());
            }
            attendanceMap.get(at.getCourse().getId()).add(
                    AttendanceDTO.builder()
                            .id(at.getId())
                            .date(at.getDate())
                            .status(at.getStatus())
                            .studentId(at.getStudent().getId())
                            .studentName(at.getStudent().getFirstName() + " " + at.getStudent().getLastName())
                            .courseId(at.getCourse().getId())
                            .courseName(at.getCourse().getName())
                            .build()
            );
        });

        return attendanceMap;

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

        return AttendanceServiceImpls.attendanceToAttendanceDTO(attendance);
    }

    @Override
    public StudentAttendanceDTO getAttendanceByStudentGroupedByCourses(Long studentId) {
        Student student = this.studentRepository.findById(studentId).orElse(null);

        if(student == null){
            throw new EntityNotFoundException("Student not found");
        }

        List<Attendance> attendances = this.attendanceRepository.findByStudentId(studentId);

        Map<Course, CourseAttendanceDTO> courseAttendanceMap = new HashMap<>();
        for (Attendance attendance: attendances) {
            if(!courseAttendanceMap.containsKey(attendance.getCourse())) {
                CourseAttendanceDTO courseAttendanceDTO = new CourseAttendanceDTO(attendance.getCourse().getId(), attendance.getCourse().getName(), new ArrayList<>());
                courseAttendanceMap.put(attendance.getCourse(), courseAttendanceDTO);
            }
            courseAttendanceMap.get(attendance.getCourse()).getAttendance().add(new AttendanceItemDTO(attendance.getId(), attendance.getDate(), attendance.getStatus()));
        }
        return new StudentAttendanceDTO(studentId, student.getFirstName(), courseAttendanceMap.values().stream().toList());
    }

    private static AttendanceDTO attendanceToAttendanceDTO(Attendance attendance){
        return  AttendanceDTO.builder()
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
