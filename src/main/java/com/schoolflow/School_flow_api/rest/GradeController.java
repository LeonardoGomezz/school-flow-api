package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.grade.GradeDTO;
import com.schoolflow.School_flow_api.services.interfaces.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeDTO>>getGradesByStudent(@PathVariable Long studentId){
        return ResponseEntity.ok(this.gradeService.getGradesByStudent(studentId));
    }

    @GetMapping("/asessessment/{assessmentId}")
    public ResponseEntity<List<GradeDTO>>getGradesByAssessment(@PathVariable Long assessmentId){
        return ResponseEntity.ok(this.gradeService.getGradesByAssessment(assessmentId));
    }

    @PostMapping
    public ResponseEntity<GradeDTO>createGrade(@RequestBody GradeDTO gradeDTO){
        return ResponseEntity.ok(this.gradeService.createGrade(gradeDTO));
    }

    @PutMapping("/{gradeId}")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable Long gradeId, @RequestBody GradeDTO gradeDTO){
        return ResponseEntity.ok(this.gradeService.updateGrade(gradeId, gradeDTO));
    }

    @DeleteMapping("/{gradeId}")
    public ResponseEntity<Object> deleteGrade(@PathVariable Long gradeId){
        this.gradeService.deleteGrade(gradeId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Message: ", "Grade delete succes"));
    }
}
