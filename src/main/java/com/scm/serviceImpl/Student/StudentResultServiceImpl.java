package com.scm.serviceImpl.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.model.StudentResult;
import com.scm.repository.student.StudentResultRepository;
import com.scm.service.Student.StudentResultService;

@Service
public class StudentResultServiceImpl implements StudentResultService{
    @Autowired
    private StudentResultRepository studentResultRepository;

    @Override
    public List<StudentResult> allSemesterResults(String studentId) {
        return studentResultRepository.findAllSemesterResult(studentId);
    }

    @Override
    public StudentResult sememsterResult(int year, int semester,String studentId) {
        return studentResultRepository.findByStudentIdAndYearAndSemester(studentId,year,semester);
    }
    
}
