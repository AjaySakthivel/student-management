package com.ajay.student_management.service;

import org.springframework.stereotype.Service;

import com.ajay.student_management.dto.StudentSummary;
import com.ajay.student_management.entity.Student;
import com.ajay.student_management.repository.StudentRepository;

@Service
public class StudentSummaryService {

    private final StudentRepository studentRepository;
    private final MarkService markService;
    private final AttendanceService attendanceService;

    public StudentSummaryService(
            StudentRepository studentRepository,
            MarkService markService,
            AttendanceService attendanceService) {

        this.studentRepository = studentRepository;
        this.markService = markService;
        this.attendanceService = attendanceService;
    }

    public StudentSummary getStudentSummary(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElse(null);

        if (student == null) {
            return null;
        }

        double averageMarks = markService.getAverageMarks(studentId);

        double attendancePercentage =
                attendanceService.getAttendancePercentage(studentId);

        return new StudentSummary(
                student.getName(),
                student.getCourse(),
                averageMarks,
                attendancePercentage
        );
    }
}