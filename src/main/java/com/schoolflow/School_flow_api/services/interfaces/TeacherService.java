package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.teacher.TeacherDTO;
import com.schoolflow.School_flow_api.entities.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    TeacherDTO getTeacherById(Long teacherId);

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO updateTeacher(Long teacherId, TeacherDTO teacherDTO);

    void deleteTeacher(Long teacherId);

    Teacher getTeacherEntityById(Long teacherId);
}
