package com.schoolflow.School_flow_api.repositories;

import com.schoolflow.School_flow_api.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("""
SELECT s FROM teachers s
WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :search, '%'))
   OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :search, '%'))
""")
    Page<Teacher> searchByName(@Param("search") String search, Pageable pageable);
}
