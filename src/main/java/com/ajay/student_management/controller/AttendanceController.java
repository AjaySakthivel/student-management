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

import com.ajay.student_management.entity.Attendance;
import com.ajay.student_management.service.AttendanceService;

@RestController
@RequestMapping("/api/students")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/{studentId}/attendance")
    public Attendance addAttendance(
            @PathVariable Long studentId,
            @RequestBody Attendance attendance) {

        return attendanceService.addAttendance(studentId, attendance);
    }

    @GetMapping("/{studentId}/attendance")
    public List<Attendance> getAttendance(
            @PathVariable Long studentId) {

        return attendanceService.getAttendanceByStudentId(studentId);
    }
    @GetMapping("/{studentId}/attendance/percentage")
    public double getAttendancePercentage(
            @PathVariable Long studentId) {

        return attendanceService.getAttendancePercentage(studentId);
    }
    @PutMapping("/attendance/{attendanceId}")
    public Attendance updateAttendance(
            @PathVariable Long attendanceId,
            @RequestBody Attendance attendance) {

        return attendanceService.updateAttendance(attendanceId, attendance);
    }
    @DeleteMapping("/attendance/{attendanceId}")
    public String deleteAttendance(@PathVariable Long attendanceId) {

        boolean deleted = attendanceService.deleteAttendance(attendanceId);

        if (!deleted) {
            return "Attendance not found with id: " + attendanceId;
        }

        return "Attendance deleted successfully";
    }
}