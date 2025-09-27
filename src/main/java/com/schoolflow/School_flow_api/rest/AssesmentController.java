package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.assesment.AssesmentDTO;
import com.schoolflow.School_flow_api.services.interfaces.AssesmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/assesments")
public class AssesmentController {

    @Autowired
    private AssesmentService assesmentService;

    @PostMapping("/{courseId}")
    public ResponseEntity<AssesmentDTO> createAssesment(@PathVariable Long courseId, @RequestBody AssesmentDTO assesmentDTO){
        return ResponseEntity.ok(this.assesmentService.createAssesment(courseId, assesmentDTO));
    }
}
