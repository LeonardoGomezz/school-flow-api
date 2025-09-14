package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.entities.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();
}
