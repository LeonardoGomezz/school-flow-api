package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.assesment.AssesmentDTO;

import java.util.List;

public interface AssesmentService {

    List<AssesmentDTO> getAllAssesmentsbyCourseId(Long courseId);

    AssesmentDTO getAssesmentById(Long assesmentId);

    AssesmentDTO createAssesment(Long courseId, AssesmentDTO assesmentDTO);

    AssesmentDTO updateAssesment(Long assesmentId, AssesmentDTO assesmentDTO);

    void deleteAssesment(Long assesmentId);
}
