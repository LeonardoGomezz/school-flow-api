package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.TeacherDTO;
import com.schoolflow.School_flow_api.entities.Teacher;
import com.schoolflow.School_flow_api.services.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        return ResponseEntity.ok(this.teacherService.getAllTeachers());
    }

    @GetMapping("{teacherId}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long teacherId){
        return ResponseEntity.ok(this.teacherService.getTeacherById(teacherId));
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
