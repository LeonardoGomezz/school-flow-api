package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.StudentDTO;
import com.schoolflow.School_flow_api.entities.DocumentType;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.repositories.DocumentTypeRepository;
import com.schoolflow.School_flow_api.repositories.StudentRepository;
import com.schoolflow.School_flow_api.services.interfaces.StudentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;


    @Override
    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = this.studentRepository.findById(studentId).orElse(null);

        if(student == null){
            throw new EntityNotFoundException("Student not found");
        }

        return new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getBirthDate(), student.getAddress(), student.getPhone(), student.getGuardianName(), student.getGuardianPhone(), student.getDocumentType().getCode(), student.getDocumentNumber());
    }

    @Transactional
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        DocumentType doctype = this.documentTypeRepository.findByCode(studentDTO.getDocumentType())
                .orElseThrow(() -> new EntityNotFoundException("Document type not found"));
        Student newStudent = new Student(studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getBirthDate(), studentDTO.getAddress(), studentDTO.getPhone(), studentDTO.getGuardianName(), studentDTO.getGuardianPhone());
        newStudent = this.studentRepository.save(newStudent);
        studentDTO.setId(newStudent.getId());
        return studentDTO;
    }

}
