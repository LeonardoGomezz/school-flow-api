package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.course.CourseDTO;
import com.schoolflow.School_flow_api.dto.student.StudentDTO;
import com.schoolflow.School_flow_api.dto.teacher.TeacherDTO;
import com.schoolflow.School_flow_api.entities.Teacher;
import com.schoolflow.School_flow_api.services.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<Page<TeacherDTO>> getAllTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<TeacherDTO> teachers;

        if (search != null && !search.trim().isEmpty()) {
            teachers = teacherService.searchTeachers(search.trim(), pageable);
        }else {
            teachers = this.teacherService.getAllTeachers(pageable);
        }
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("{teacherId}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long teacherId){
        return ResponseEntity.ok(this.teacherService.getTeacherById(teacherId));
    }

    @GetMapping("/courses/{teacherId}")
    public ResponseEntity<List<CourseDTO>>getTeacherCourses(@PathVariable Long teacherId){
        return ResponseEntity.ok(this.teacherService.getTeacherCourses(teacherId));
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO){
        return ResponseEntity.ok(this.teacherService.createTeacher(teacherDTO));
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDTO teacherDTO){
        return ResponseEntity.ok(this.teacherService.updateTeacher(teacherId, teacherDTO));
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable Long teacherId){
        this.teacherService.deleteTeacher(teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Message: ", "Teacher delete succes"));
    }
}
