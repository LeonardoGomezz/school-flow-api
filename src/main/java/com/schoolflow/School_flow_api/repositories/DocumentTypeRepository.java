package com.schoolflow.School_flow_api.repositories;

import com.schoolflow.School_flow_api.entities.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
    Optional<DocumentType> findByCode(String code);
}
