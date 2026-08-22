package com.ajay.student_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ajay.student_management.entity.Attendance;
import com.ajay.student_management.entity.Student;
import com.ajay.student_management.repository.AttendanceRepository;
import com.ajay.student_management.repository.StudentRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    public Attendance addAttendance(Long studentId, Attendance attendance) {

        Student student = studentRepository.findById(studentId)
                .orElse(null);

        if (student == null) {
            return null;
        }

        attendance.setStudent(student);

        return attendanceRepository.save(attendance);
    }
    public double getAttendancePercentage(Long studentId) {

        List<Attendance> attendanceList =
                attendanceRepository.findByStudentId(studentId);

        if (attendanceList.isEmpty()) {
            return 0.0;
        }

        long presentDays = attendanceList.stream()
                .filter(attendance ->
                        attendance.getStatus().equalsIgnoreCase("PRESENT"))
                .count();

        return (presentDays * 100.0) / attendanceList.size();
    }
    public Attendance updateAttendance(Long attendanceId, Attendance updatedAttendance) {

        Attendance existingAttendance = attendanceRepository.findById(attendanceId)
                .orElse(null);

        if (existingAttendance == null) {
            return null;
        }

        existingAttendance.setDate(updatedAttendance.getDate());
        existingAttendance.setStatus(updatedAttendance.getStatus());

        return attendanceRepository.save(existingAttendance);
    }
    public boolean deleteAttendance(Long attendanceId) {

        if (!attendanceRepository.existsById(attendanceId)) {
            return false;
        }

        attendanceRepository.deleteById(attendanceId);
        return true;
    }
    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }
}