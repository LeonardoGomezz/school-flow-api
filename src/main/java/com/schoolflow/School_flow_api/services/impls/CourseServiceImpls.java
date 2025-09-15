package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.CourseDTO;
import com.schoolflow.School_flow_api.entities.Course;
import com.schoolflow.School_flow_api.repositories.CourseRepository;
import com.schoolflow.School_flow_api.services.interfaces.CourseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpls implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    @Override
    public CourseDTO getCourseById(Long courseId) {
        Course course = this.courseRepository.findById(courseId).orElse(null);

        if(course == null){
            throw  new EntityNotFoundException("Course not found");
        }

        CourseDTO  courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setGrade(course.getGrade());
        courseDTO.setSchoolYear(course.getSchoolYear());

        if(course.getTeacher() != null){
            courseDTO.setTeacherId(course.getTeacher().getId());
        }else{
            courseDTO.setTeacherId(null);
        }
        return courseDTO;
    }

    @Transactional
    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course newCourse = new Course(
                courseDTO.getName(),
                courseDTO.getGrade(),
                courseDTO.getSchoolYear()
        );
        newCourse = this.courseRepository.save(newCourse);
        courseDTO.setId(newCourse.getId());
        return courseDTO;
    }

    @Override
    public CourseDTO updateCourse(Long courseId, CourseDTO courseDTO) {
        Course existingCourse = this.courseRepository.findById(courseId).orElse(null);

        if (existingCourse == null){
            throw new EntityNotFoundException("Course not found");
        }

        existingCourse.setName(courseDTO.getName());
        existingCourse.setGrade(courseDTO.getGrade());
        existingCourse.setSchoolYear(courseDTO.getSchoolYear());

        this.courseRepository.save(existingCourse);

        return courseDTO;
    }

}
