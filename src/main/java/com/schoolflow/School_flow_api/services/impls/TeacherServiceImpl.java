package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.TeacherDTO;
import com.schoolflow.School_flow_api.entities.Teacher;
import com.schoolflow.School_flow_api.repositories.TeacherRepository;
import com.schoolflow.School_flow_api.services.interfaces.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Override
    public TeacherDTO getTeacherById(Long teacherId) {
        Teacher teacher = this.teacherRepository.findById(teacherId).orElse(null);

        if (teacher == null){
            throw new EntityNotFoundException("Teacher not found");
        }

        return new TeacherDTO(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getAcademicBackground(),
                teacher.getAddress(),
                teacher.getPhone()
        );
    }

    @Transactional
    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher newTeacher = new Teacher(
                teacherDTO.getFirstName(),
                teacherDTO.getLastName(),
                teacherDTO.getAcademicBackground(),
                teacherDTO.getAddress(),
                teacherDTO.getPhone()
        );
        newTeacher = this.teacherRepository.save(newTeacher);
        teacherDTO.setId(newTeacher.getId());
        return teacherDTO;
    }
}
