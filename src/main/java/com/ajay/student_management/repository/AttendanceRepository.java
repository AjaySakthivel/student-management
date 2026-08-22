package com.ajay.student_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.student_management.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);
}