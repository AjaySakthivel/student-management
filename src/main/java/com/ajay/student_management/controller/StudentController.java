package com.ajay.student_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.student_management.entity.Student;
import com.ajay.student_management.service.StudentService;
import com.ajay.student_management.service.StudentSummaryService;
import com.ajay.student_management.dto.StudentSummary;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController 
{

    private final StudentService studentService;
    private final StudentSummaryService studentSummaryService;

    public StudentController(
            StudentService studentService,
            StudentSummaryService studentSummaryService) {

        this.studentService = studentService;
        this.studentSummaryService = studentSummaryService;
    } 
    @GetMapping
    public List<Student> getAllStudents() 
    {
        return studentService.getAllStudents();
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.addStudent(student);
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,@Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        boolean deleted = studentService.deleteStudent(id);

        if (!deleted) {
            return "Student not found with id: " + id;
        }

        return "Student deleted successfully";
    }
    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String name) {
        return studentService.searchStudentsByName(name);
    }
    @GetMapping("/{id}/summary")
    public StudentSummary getStudentSummary(@PathVariable Long id) {

        return studentSummaryService.getStudentSummary(id);
    }

}