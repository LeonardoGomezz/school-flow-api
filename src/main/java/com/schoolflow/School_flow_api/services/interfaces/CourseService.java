package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.CourseDTO;
import com.schoolflow.School_flow_api.dto.EnrolledStudentDTO;
import com.schoolflow.School_flow_api.dto.StudentDTO;
import com.schoolflow.School_flow_api.entities.Course;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(Long courseId);

    List<EnrolledStudentDTO> getStudentsByCourseId(Long courseId);

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(Long courseId, CourseDTO courseDTO);

    CourseDTO asignTeacherToCourse(Long courseId, Long teachirId);

    CourseDTO unAsignTeacherToCourse(Long courseId, Long teacherId);

    void incorporateStudentToCourse(Long studentId, Long courseId);
}
