package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.entities.Teacher;
import com.schoolflow.School_flow_api.repositories.TeacherRepository;
import com.schoolflow.School_flow_api.services.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }
}
