package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.assesment.AssesmentDTO;
import com.schoolflow.School_flow_api.services.interfaces.AssesmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/{assesmentId}")
    public ResponseEntity<AssesmentDTO> updateAssesment(@PathVariable Long assesmentId, @RequestBody AssesmentDTO assesmentDTO){
        return ResponseEntity.ok(this.assesmentService.updateAssesment(assesmentId, assesmentDTO));
    }

    @DeleteMapping("/{assesmentId}")
    public ResponseEntity<Object>deleteAssesment(@PathVariable Long assesmentId){
        this.assesmentService.deleteAssesment(assesmentId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Message: ", "Assessment delete succes"));
    }
}
