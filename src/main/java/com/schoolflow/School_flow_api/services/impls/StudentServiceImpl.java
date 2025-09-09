package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.StudentDTO;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.repositories.StudentRepository;
import com.schoolflow.School_flow_api.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student newStudent = new Student(studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getBirthDate(), studentDTO.getAddress(), studentDTO.getPhone(), studentDTO.getGuardianName(), studentDTO.getGuardianPhone());
        newStudent = this.studentRepository.save(newStudent);
        studentDTO.setId(newStudent.getId());
        return studentDTO;
    }

}
