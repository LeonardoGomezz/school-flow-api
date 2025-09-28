package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.grade.GradeDTO;
import com.schoolflow.School_flow_api.entities.Assesment;
import com.schoolflow.School_flow_api.entities.Grade;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.repositories.AssesmentRepository;
import com.schoolflow.School_flow_api.repositories.GradeRepository;
import com.schoolflow.School_flow_api.repositories.StudentRepository;
import com.schoolflow.School_flow_api.services.interfaces.GradeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private AssesmentRepository assesmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Student student = this.studentRepository.findById(gradeDTO.getStudentId()).orElse(null);
        Assesment assesment = this.assesmentRepository.findById(gradeDTO.getAssesmentId()).orElse(null);

        if (student== null){
            throw new EntityNotFoundException("Student not found");
        }

        if (assesment== null){
            throw new EntityNotFoundException("Assessment not found");
        }

        Grade grade = new Grade(gradeDTO.getStudentId(), gradeDTO.getAssesmentId(), gradeDTO.getScore());
        grade = this.gradeRepository.save(grade);
        gradeDTO.setId(grade.getId());
        return gradeDTO;
    }
}
