package com.schoolflow.School_flow_api.repositories;

import com.schoolflow.School_flow_api.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    Optional<Inscription> findByStudentIdAndCourseId(Long studentId, Long courseId);
    List<Inscription> findByCourseId(Long courseId);
}
