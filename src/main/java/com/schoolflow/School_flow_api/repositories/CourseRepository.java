package com.schoolflow.School_flow_api.repositories;

import com.schoolflow.School_flow_api.dto.course.CourseDTO;
import com.schoolflow.School_flow_api.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findCourseByTeacherId(Long teacherId);
}
