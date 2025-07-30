package com.scm.service.admin;

import java.util.List;

import com.scm.model.Student;

public interface StudentProfilesService {
    public List<Student> getAllStudent();
    public Student getProfileByID(String studentID);
}
