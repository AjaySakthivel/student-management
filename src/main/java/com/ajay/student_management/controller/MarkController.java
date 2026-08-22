package com.ajay.student_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.student_management.entity.Mark;
import com.ajay.student_management.service.MarkService;

@RestController
@RequestMapping("/api/students")
public class MarkController {

    private final MarkService markService;

    public MarkController(MarkService markService)
    {
        this.markService = markService;
    }

    @PostMapping("/{studentId}/marks")
    public Mark addMark(
            @PathVariable Long studentId,
            @RequestBody Mark mark) {

        return markService.addMark(studentId, mark);
    }

    @GetMapping("/{studentId}/marks")
    public List<Mark> getMarks(
            @PathVariable Long studentId) {

        return markService.getMarksByStudentId(studentId);
    }
    @PutMapping("/marks/{markId}")
    public Mark updateMark(
            @PathVariable Long markId,
            @RequestBody Mark mark) {

        return markService.updateMark(markId, mark);
    }
    @DeleteMapping("/marks/{markId}")
    public String deleteMark(@PathVariable Long markId) {

        boolean deleted = markService.deleteMark(markId);

        if (!deleted) {
            return "Mark not found with id: " + markId;
        }

        return "Mark deleted successfully";
    }
    @GetMapping("/{studentId}/marks/average")
    public double getAverageMarks(
            @PathVariable Long studentId) {

        return markService.getAverageMarks(studentId);
    }
}