package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.course.CourseDTO;
import com.schoolflow.School_flow_api.dto.student.StudentDTO;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.services.interfaces.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/api/v1/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(this.studentService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO>getStudentById(@PathVariable Long studentId){
        return ResponseEntity.ok(this.studentService.getStudentById(studentId));
    }

    @GetMapping("/courses/{studentId}")
    public ResponseEntity<List<CourseDTO>>getStudentCourses(@PathVariable Long studentId){
        return ResponseEntity.ok(this.studentService.getStudentCourses(studentId));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(this.studentService.createStudent(studentDTO));
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO>updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(this.studentService.updateStudent(studentId, studentDTO));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Object>deleteStudent(@PathVariable Long studentId){
        this.studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Message: ", "student delete success"));
    }
}
