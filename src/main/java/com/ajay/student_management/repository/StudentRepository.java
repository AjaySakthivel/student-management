package com.ajay.student_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.student_management.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>
{
	List<Student> findByNameContainingIgnoreCase(String name);
}
