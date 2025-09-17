package com.schoolflow.School_flow_api.rest;

import com.schoolflow.School_flow_api.dto.InscriptionDTO;
import com.schoolflow.School_flow_api.services.interfaces.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @PutMapping("/{studentId}/includes/{courseId}")
    public ResponseEntity<Object> includeStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        this.inscriptionService.includeStudentToCourse(studentId, courseId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Message: ", "Student asigned succes"));
    }
}