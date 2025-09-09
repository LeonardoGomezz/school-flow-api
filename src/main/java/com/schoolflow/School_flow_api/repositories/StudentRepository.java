package com.schoolflow.School_flow_api.repositories;

import com.schoolflow.School_flow_api.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
