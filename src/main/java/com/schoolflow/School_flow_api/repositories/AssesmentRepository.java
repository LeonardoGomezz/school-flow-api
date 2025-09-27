package com.schoolflow.School_flow_api.repositories;

import com.schoolflow.School_flow_api.entities.Assesment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssesmentRepository extends JpaRepository<Assesment, Long> {
}
