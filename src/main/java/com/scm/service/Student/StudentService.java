package com.scm.service.Student;

import com.scm.model.Student;

public interface StudentService {
    public Student getStudentProfileByToken(String token);
    public Student getStudentProfileById(String studentId);
    public String getStudentIdByToken(String token);
}
