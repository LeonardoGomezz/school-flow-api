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

@Service
public class AssesmentServiceImpl implements AssesmentService {

    @Autowired
    private AssesmentRepository assesmentRepository;

    @Autowired
    private CourseRepository courseRepository;

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
}
