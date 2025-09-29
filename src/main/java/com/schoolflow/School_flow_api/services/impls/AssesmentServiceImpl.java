package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.assesment.AssesmentDTO;
import com.schoolflow.School_flow_api.entities.Assesment;
import com.schoolflow.School_flow_api.entities.Course;
import com.schoolflow.School_flow_api.repositories.AssesmentRepository;
import com.schoolflow.School_flow_api.repositories.CourseRepository;
import com.schoolflow.School_flow_api.services.interfaces.AssesmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssesmentServiceImpl implements AssesmentService {

    @Autowired
    private AssesmentRepository assesmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<AssesmentDTO> getAllAssesments() {
        return this.assesmentRepository.findAll().stream().map(
                assesment -> AssesmentDTO.builder()
                        .id(assesment.getId())
                        .title(assesment.getTitle())
                        .description(assesment.getDescription())
                        .type(assesment.getType())
                        .percentage(assesment.getPercentage())
                        .period(assesment.getPeriod())
                        .schoolYear(assesment.getSchoolYear())
                        .build()).toList();
    }

    @Override
    public List<AssesmentDTO> getAllAssesmentsbyCourseId(Long courseId) {
        Course course = this.courseRepository.findById(courseId).orElse(null);

        if (course == null){
            throw new EntityNotFoundException("Course not found");
        }

        return this.assesmentRepository.findByCourseId(courseId).stream()
                .map(assesment -> AssesmentDTO.builder()
                        .id(assesment.getId())
                        .title(assesment.getTitle())
                        .description(assesment.getDescription())
                        .type(assesment.getType())
                        .percentage(assesment.getPercentage())
                        .period(assesment.getPeriod())
                        .schoolYear(assesment.getSchoolYear())
                        .build()
                ).toList();
    }

    @Override
    public AssesmentDTO getAssesmentById(Long assesmentId) {
        Assesment assesment = this.assesmentRepository.findById(assesmentId).orElse(null);

        if (assesment == null){
            throw new EntityNotFoundException("Assessment not found");
        }

        return AssesmentDTO.builder()
                .id(assesmentId)
                .title(assesment.getTitle())
                .description(assesment.getDescription())
                .type(assesment.getType())
                .percentage(assesment.getPercentage())
                .period(assesment.getPeriod())
                .schoolYear(assesment.getSchoolYear())
                .build();
    }

    @Transactional
    @Override
    public AssesmentDTO createAssesment(Long courseId, AssesmentDTO assesmentDTO) {
        Course course = this.courseRepository.findById(courseId).orElse(null);

        if (course == null){
            throw new EntityNotFoundException("Course not found");
        }

        Assesment newAssesment = new Assesment(
                assesmentDTO.getTitle(),
                assesmentDTO.getDescription(),
                assesmentDTO.getType(),
                assesmentDTO.getPercentage(),
                assesmentDTO.getPeriod(),
                assesmentDTO.getSchoolYear()
        );
        newAssesment.setCourse(course);

        newAssesment = this.assesmentRepository.save(newAssesment);

        return new AssesmentDTO(
                newAssesment.getId(),
                newAssesment.getTitle(),
                newAssesment.getDescription(),
                newAssesment.getType(),
                newAssesment.getPercentage(),
                newAssesment.getPeriod(),
                newAssesment.getSchoolYear()
        );
    }

    @Override
    @Transactional
    public AssesmentDTO updateAssesment(Long assesmentId, AssesmentDTO assesmentDTO) {
        Assesment assesment = this.assesmentRepository.findById(assesmentId).orElse(null);

        if (assesment == null ){
            throw new EntityNotFoundException("Assessment not found");
        }

        assesment.setTitle(assesmentDTO.getTitle());
        assesment.setDescription(assesmentDTO.getDescription());
        assesment.setType(assesment.getType());
        assesment.setPercentage(assesment.getPercentage());
        assesment.setPeriod(assesment.getPeriod());
        assesment.setSchoolYear(assesment.getSchoolYear());

        this.assesmentRepository.save(assesment);

        return assesmentDTO;
    }

    @Override
    @Transactional
    public void deleteAssesment(Long assesmentId) {
        this.assesmentRepository.deleteById(assesmentId);
    }
}
