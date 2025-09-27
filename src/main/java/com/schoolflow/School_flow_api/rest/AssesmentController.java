package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.assesment.AssesmentDTO;
import com.schoolflow.School_flow_api.services.interfaces.AssesmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assesments")
public class AssesmentController {

    @Autowired
    private AssesmentService assesmentService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AssesmentDTO>>getAllAssesmentsbyCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(this.assesmentService.getAllAssesmentsbyCourseId(courseId));
    }

    @GetMapping("/{assesmentId}")
    public ResponseEntity<AssesmentDTO>getAssesmentById(@PathVariable Long assesmentId){
        return ResponseEntity.ok(this.assesmentService.getAssesmentById(assesmentId));
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<AssesmentDTO> createAssesment(@PathVariable Long courseId, @RequestBody AssesmentDTO assesmentDTO){
        return ResponseEntity.ok(this.assesmentService.createAssesment(courseId, assesmentDTO));
    }
}
