package com.schoolflow.School_flow_api.repositories;

import com.schoolflow.School_flow_api.entities.DocumentType;
import com.schoolflow.School_flow_api.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByDocumentTypeAndDocumentNumber(DocumentType documentType, String documentNumber);

    @Query("""
SELECT s FROM students s
WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :search, '%'))
   OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :search, '%'))
   OR s.documentNumber LIKE CONCAT('%', :search, '%')
""")
    Page<Student> searchByNameOrDocument(@Param("search") String search, Pageable pageable);


}
