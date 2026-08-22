package com.ajay.student_management.dto;

public class StudentSummary {

    private String studentName;
    private String course;
    private double averageMarks;
    private double attendancePercentage;

    public StudentSummary() {
    }

    public StudentSummary(String studentName,
                          String course,
                          double averageMarks,
                          double attendancePercentage) {
        this.studentName = studentName;
        this.course = course;
        this.averageMarks = averageMarks;
        this.attendancePercentage = attendancePercentage;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourse() {
        return course;
    }

    public double getAverageMarks() {
        return averageMarks;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }
}