package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.assesment.AssesmentDTO;

public interface AssesmentService {
    AssesmentDTO createAssesment(Long courseId, AssesmentDTO assesmentDTO);
}
