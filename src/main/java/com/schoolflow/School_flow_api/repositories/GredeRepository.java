package com.schoolflow.School_flow_api.repositories;

import com.schoolflow.School_flow_api.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GredeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByAssesmentId(Long assesmentId);
}
