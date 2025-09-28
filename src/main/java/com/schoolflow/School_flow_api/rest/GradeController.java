package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.grade.GradeDTO;
import com.schoolflow.School_flow_api.services.interfaces.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    public ResponseEntity<GradeDTO>createGrade(@RequestBody GradeDTO gradeDTO){
        return ResponseEntity.ok(this.gradeService.createGrade(gradeDTO));
    }
}
