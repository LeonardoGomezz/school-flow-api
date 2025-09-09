package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.StudentDTO;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.services.interfaces.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(this.studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(this.studentService.createStudent(studentDTO));
    }
}
