package com.scm.service.Student;

import java.util.List;

import com.scm.model.StudentResult;

public interface StudentResultService {
    public List<StudentResult> allSemesterResults(String studentId); 
    public StudentResult sememsterResult(int year,int semester,String studentId);   
}