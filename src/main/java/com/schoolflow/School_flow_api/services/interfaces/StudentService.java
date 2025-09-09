package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.StudentDTO;
import com.schoolflow.School_flow_api.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    StudentDTO createStudent(StudentDTO studentDTO);
}
