package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.CourseDTO;
import com.schoolflow.School_flow_api.entities.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    CourseDTO getCourseById(Long courseId);

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(Long courseId, CourseDTO courseDTO);
}
