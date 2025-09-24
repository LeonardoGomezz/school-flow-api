package com.schoolflow.School_flow_api.repositories;

import com.schoolflow.School_flow_api.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByCourseId(Long courseId);
    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findByCourseIdAndDate(Long courseId, LocalDate date);
    List<Attendance> findByStudentIdAndDate(Long studentId, LocalDate date);
    List<Attendance> findByCourseIdAndDateBetween(Long courseId, LocalDate startDate, LocalDate endDate);
    List<Attendance> findByStudentIdAndDateBetween(Long studentId, LocalDate startDate, LocalDate endDate);
}
