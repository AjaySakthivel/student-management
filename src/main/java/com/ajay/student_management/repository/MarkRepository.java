package com.ajay.student_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.student_management.entity.Mark;

public interface MarkRepository extends JpaRepository<Mark, Long> {

    List<Mark> findByStudentId(Long studentId);
}