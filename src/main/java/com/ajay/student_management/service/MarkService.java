package com.ajay.student_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ajay.student_management.entity.Mark;
import com.ajay.student_management.entity.Student;
import com.ajay.student_management.repository.MarkRepository;
import com.ajay.student_management.repository.StudentRepository;

@Service
public class MarkService {

    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;

    public MarkService(MarkRepository markRepository,
                       StudentRepository studentRepository) {
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
    }

    public Mark addMark(Long studentId, Mark mark) {

        Student student = studentRepository.findById(studentId)
                .orElse(null);

        if (student == null) {
            return null;
        }

        mark.setStudent(student);

        return markRepository.save(mark);
    }
    public Mark updateMark(Long markId, Mark updatedMark) {

        Mark existingMark = markRepository.findById(markId)
                .orElse(null);

        if (existingMark == null) {
            return null;
        }

        existingMark.setSubject(updatedMark.getSubject());
        existingMark.setMarks(updatedMark.getMarks());

        return markRepository.save(existingMark);
    }
    public boolean deleteMark(Long markId) {

        if (!markRepository.existsById(markId)) {
            return false;
        }

        markRepository.deleteById(markId);
        return true;
    }
    public double getAverageMarks(Long studentId) {

        List<Mark> marks = markRepository.findByStudentId(studentId);

        if (marks.isEmpty()) {
            return 0.0;
        }

        int totalMarks = marks.stream()
                .mapToInt(Mark::getMarks)
                .sum();

        return (double) totalMarks / marks.size();
    }

    public List<Mark> getMarksByStudentId(Long studentId) {
        return markRepository.findByStudentId(studentId);
    }
}