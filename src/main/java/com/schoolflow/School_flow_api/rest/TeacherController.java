package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.entities.Teacher;
import com.schoolflow.School_flow_api.services.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        return ResponseEntity.ok(this.teacherService.getAllTeachers());
    }
}
