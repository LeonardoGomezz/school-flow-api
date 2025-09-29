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

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private AssesmentRepository assesmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<GradeDTO> getGradesByStudent(Long studentId) {
        Student student = this.studentRepository.findById(studentId).orElse(null);

        if (student == null){
            throw new EntityNotFoundException("Student not found");
        }

        return gradeRepository.findByStudentId(studentId).stream().map(
                grade -> GradeDTO.builder()
                        .id(grade.getId())
                        .studentId(studentId)
                        .assesmentId(grade.getAssesment().getId())
                        .score(grade.getScore())
                        .build()).toList();
    }

    @Override
    public List<GradeDTO> getGradesByAssessment(Long assessmentId) {
        Assesment assesment = this.assesmentRepository.findById(assessmentId).orElse(null);

        if (assesment == null){
            throw new EntityNotFoundException("Assessment not found");
        }

        return gradeRepository.findByAssesmentId(assessmentId).stream().map(
                grade -> GradeDTO.builder()
                        .id(grade.getId())
                        .studentId(grade.getStudent().getId())
                        .assesmentId(grade.getAssesment().getId())
                        .score(grade.getScore())
                        .build()).toList();
    }

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

    @Override
    @Transactional
    public GradeDTO updateGrade(Long gradeId, GradeDTO gradeDTO) {
        Grade grade = this.gradeRepository.findById(gradeId).orElse(null);

        if (grade == null){
            throw new EntityNotFoundException("Grade not found");
        }

        grade.setScore(gradeDTO.getScore());

        this.gradeRepository.save(grade);

        return gradeDTO;
    }

    @Override
    @Transactional
    public void deleteGrade(Long gradeId) {
        this.gradeRepository.deleteById(gradeId);
    }
}
