package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.entities.Course;
import com.schoolflow.School_flow_api.entities.Inscription;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.repositories.CourseRepository;
import com.schoolflow.School_flow_api.repositories.InscriptionRepository;
import com.schoolflow.School_flow_api.repositories.StudentRepository;
import com.schoolflow.School_flow_api.services.interfaces.InscriptionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    @Override
    public void includeStudentToCourse(Long studentId, Long courseId) {
        Course course = this.courseRepository.findById(courseId).orElse(null);
        Student student = this.studentRepository.findById(studentId).orElse(null);

        if (course == null){
            throw new EntityNotFoundException("course not found");
        }

        if (student == null){
            throw new EntityNotFoundException("Student not found");
        }

        if(inscriptionRepository.findByStudentIdAndCourseId(studentId, courseId).isPresent()){
            throw new IllegalStateException("Student already enrrolled in this course");
        }

        Inscription inscription = new Inscription();
        inscription.setStudent(student);
        inscription.setCourse(course);
        inscription.setInscriptionDate(LocalDate.now());

        inscription = inscriptionRepository.save(inscription);
    }

    @Transactional
    @Override
    public void excludeStudentToCourse(Long studentId, Long courseId) {
        Course course = this.courseRepository.findById(courseId).orElse(null);
        Student studen = this.studentRepository.findById(studentId).orElse(null);
        Inscription inscription = this.inscriptionRepository.findByStudentIdAndCourseId(studentId, courseId).orElse(null);

        if (course == null){
            throw new EntityNotFoundException("Course not found");
        }

        if (studen == null){
            throw new EntityNotFoundException("Student not found");
        }

        if (inscription == null){
            throw new EntityNotFoundException("Inscription not found");
        }

        this.inscriptionRepository.delete(inscription);
    }
}
