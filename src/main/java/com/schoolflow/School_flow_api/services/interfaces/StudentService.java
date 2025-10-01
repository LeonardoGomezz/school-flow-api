package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.course.CourseDTO;
import com.schoolflow.School_flow_api.dto.student.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    Page<StudentDTO> getAllStudents(Pageable pageable);

    StudentDTO getStudentById(Long studentId);

    List<CourseDTO> getStudentCourses(Long studentId);

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(Long studentId, StudentDTO studentDTO);

    void deleteStudent(Long studentId);

}
