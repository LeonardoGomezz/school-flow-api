package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.CourseDTO;
import com.schoolflow.School_flow_api.dto.EnrolledStudentDTO;
import com.schoolflow.School_flow_api.dto.TeacherDTO;
import com.schoolflow.School_flow_api.entities.Course;
import com.schoolflow.School_flow_api.entities.Inscription;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.entities.Teacher;
import com.schoolflow.School_flow_api.repositories.CourseRepository;
import com.schoolflow.School_flow_api.repositories.InscriptionRepository;
import com.schoolflow.School_flow_api.services.interfaces.CourseService;
import com.schoolflow.School_flow_api.services.interfaces.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpls implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Override
    public List<CourseDTO> getAllCourses() {
        return this.courseRepository.findAll().stream()
                .map(course -> CourseDTO.builder()
                        .id(course.getId())
                        .teacherId(course.getTeacher() != null ? course.getTeacher().getId() : null)
                        .name(course.getName())
                        .grade(course.getGrade())
                        .build()
                )
                .toList();
    }

    @Override
    public CourseDTO getCourseById(Long courseId) {
        Course course = this.courseRepository.findById(courseId).orElse(null);

        if(course == null){
            throw  new EntityNotFoundException("Course not found");
        }

        Teacher teacher = course.getTeacher();

        TeacherDTO teacherDTO = null;

        if (teacher != null) {
            teacherDTO = TeacherDTO.builder()
                    .id(teacher.getId())
                    .firstName(teacher.getFirstName())
                    .lastName(teacher.getLastName())
                    .academicBackground(teacher.getAcademicBackground())
                    .address(teacher.getAddress())
                    .phone(teacher.getPhone())
                    .build();
        }

        return CourseDTO.builder()
                .id(course.getId())
                .teacher(teacherDTO)
                .name(course.getName())
                .grade(course.getGrade())
                .build();
    }

    @Override
    public List<EnrolledStudentDTO> getStudentsByCourseId(Long courseId) {
        List<Inscription> inscriptions = this.inscriptionRepository.findByCourseId(courseId);

        return inscriptions.stream()
                .map(inscription -> {
                    Student student = inscription.getStudent();
                    return new EnrolledStudentDTO(
                            student.getId(),
                            student.getFirstName(),
                            student.getLastName(),
                            student.getDocumentType().getCode(),
                            student.getDocumentNumber()
                    );
                })
                .toList();
    }

    @Transactional
    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course newCourse = new Course(
                courseDTO.getName(),
                courseDTO.getGrade()
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
        this.courseRepository.save(existingCourse);

        return courseDTO;
    }

    @Override
    public CourseDTO asignTeacherToCourse(Long courseId, Long teacherId) {
        Course course = this.courseRepository.findById(courseId).orElse(null);
        Teacher teacher = this.teacherService.getTeacherEntityById(teacherId);

        if (course == null){
            throw new EntityNotFoundException("course not found");
        }

        if (teacher == null){
            throw new EntityNotFoundException("course not found");
        }

        course.setTeacher(teacher);

        this.courseRepository.save(course);

        return CourseDTO.builder()
                .id(course.getId())
                .teacherId(teacherId)
                .name(course.getName())
                .grade(course.getGrade())
                .build();
    }

    @Override
    public CourseDTO unAsignTeacherToCourse(Long courseId, Long teacherId) {
        Course course = this.courseRepository.findById(courseId).orElse(null);
        Teacher teacher = this.teacherService.getTeacherEntityById(teacherId);

        if (course == null){
            throw new EntityNotFoundException("course not found");
        }

        if (teacher == null){
            throw new EntityNotFoundException("course not found");
        }

        course.setTeacher(null);
        this.courseRepository.save(course);
        return CourseDTO.builder()
                .id(course.getId())
                .teacherId(teacherId)
                .name(course.getName())
                .grade(course.getGrade())
                .build();
    }

    @Override
    public void incorporateStudentToCourse(Long studentId, Long courseId) {

    }

}
