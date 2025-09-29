package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.grade.GradeDTO;

import java.util.List;

public interface GradeService {

    List<GradeDTO> getGradesByStudent(Long studentId);

    List<GradeDTO> getGradesByAssessment(Long assessmentId);

    GradeDTO createGrade(GradeDTO gradeDTO);

    GradeDTO updateGrade(Long gradeId, GradeDTO gradeDTO);

    void deleteGrade(Long gradeId);
}
