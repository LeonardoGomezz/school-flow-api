package com.schoolflow.School_flow_api.services.impls;

import com.schoolflow.School_flow_api.dto.course.CourseDTO;
import com.schoolflow.School_flow_api.dto.student.StudentDTO;
import com.schoolflow.School_flow_api.entities.DocumentType;
import com.schoolflow.School_flow_api.entities.Student;
import com.schoolflow.School_flow_api.repositories.DocumentTypeRepository;
import com.schoolflow.School_flow_api.repositories.InscriptionRepository;
import com.schoolflow.School_flow_api.repositories.StudentRepository;
import com.schoolflow.School_flow_api.services.interfaces.StudentService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Override
    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .birthDate(student.getBirthDate())
                        .address(student.getAddress())
                        .phone(student.getPhone())
                        .guardianName(student.getGuardianName())
                        .guardianPhone(student.getGuardianPhone())
                        .documentType(student.getDocumentType().getCode())
                        .documentNumber(student.getDocumentNumber())
                        .gradeName(
                                student.getInscriptions().stream()
                                        .findFirst()
                                        .map(ins -> ins.getCourse().getGrade())
                                        .orElse(null)
                        )
                        .build()
                );
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = this.studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Integer grade = student.getInscriptions().stream()
                .findFirst()
                .map(ins -> ins.getCourse().getGrade())
                .orElse(null);

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getBirthDate(),
                student.getAddress(),
                student.getPhone(),
                student.getGuardianName(),
                student.getGuardianPhone(),
                student.getDocumentType().getCode(),
                student.getDocumentNumber(),
                grade
        );
    }


    @Override
    public List<CourseDTO> getStudentCourses(Long studentId) {
        Student student = this.studentRepository.findById(studentId).orElse(null);

        if (student == null){
            throw new EntityNotFoundException("Student not found");
        }

        return this.inscriptionRepository.findCoursesByStudentId(studentId)
                .stream()
                .map(course -> CourseDTO.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .grade(course.getGrade())
                        .teacherId(course.getTeacher() != null ? course.getTeacher().getId() : null)
                        .build())
                .toList();
    }

    @Transactional
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        DocumentType doctype = this.documentTypeRepository.findByCode(studentDTO.getDocumentType()).orElse(null);

        if (doctype == null){
            throw new EntityNotFoundException("Document type not found");
        }

        boolean exists = this.studentRepository
                .existsByDocumentTypeAndDocumentNumber(doctype, studentDTO.getDocumentNumber());

        if (exists) {
            throw new EntityExistsException("Ya existe un estudiante con ese número de documento.");
        }

        Student newStudent = new Student(studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getBirthDate(), studentDTO.getAddress(), studentDTO.getPhone(), studentDTO.getGuardianName(), studentDTO.getGuardianPhone(), doctype, studentDTO.getDocumentNumber());
        newStudent = this.studentRepository.save(newStudent);
        studentDTO.setId(newStudent.getId());
        return studentDTO;
    }

    @Transactional
    @Override
    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {
        Student existingStudent = this.studentRepository.findById(studentId).orElse(null);
        DocumentType docType = this.documentTypeRepository.findByCode(studentDTO.getDocumentType()).orElse(null);

        if (existingStudent == null){
            throw new EntityNotFoundException("Student not found");
        }

        if (docType == null){
            throw new EntityNotFoundException("Document type not found");
        }

        existingStudent.setFirstName(studentDTO.getFirstName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setBirthDate(studentDTO.getBirthDate());
        existingStudent.setAddress(studentDTO.getAddress());
        existingStudent.setPhone(studentDTO.getPhone());
        existingStudent.setGuardianName(studentDTO.getGuardianName());
        existingStudent.setGuardianPhone(studentDTO.getGuardianPhone());
        existingStudent.setDocumentType(docType);
        existingStudent.setDocumentNumber(studentDTO.getDocumentNumber());
        this.studentRepository.save(existingStudent);
        return studentDTO;
    }

    @Transactional
    @Override
    public void deleteStudent(Long studentId) {
        this.studentRepository.deleteById(studentId);
    }

}
