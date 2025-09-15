package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.CourseDTO;
import com.schoolflow.School_flow_api.entities.Course;
import com.schoolflow.School_flow_api.services.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(this.courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long courseId){
        return ResponseEntity.ok(this.courseService.getCourseById(courseId));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO){
        return ResponseEntity.ok(this.courseService.createCourse(courseDTO));
    }
}
