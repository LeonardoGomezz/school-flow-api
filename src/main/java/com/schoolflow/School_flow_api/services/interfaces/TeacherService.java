package com.schoolflow.School_flow_api.services.interfaces;

import com.schoolflow.School_flow_api.dto.course.CourseDTO;
import com.schoolflow.School_flow_api.dto.teacher.TeacherDTO;
import com.schoolflow.School_flow_api.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    Page<TeacherDTO> getAllTeachers(Pageable pageable);

    TeacherDTO getTeacherById(Long teacherId);

    List<CourseDTO> getTeacherCourses(Long teacherId);

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO updateTeacher(Long teacherId, TeacherDTO teacherDTO);

    void deleteTeacher(Long teacherId);

    Teacher getTeacherEntityById(Long teacherId);

    Page<TeacherDTO> searchTeachers(String search, Pageable pageable);
}
