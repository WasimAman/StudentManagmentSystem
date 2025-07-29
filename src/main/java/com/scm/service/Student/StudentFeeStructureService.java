package com.scm.service.Student;

import java.util.List;

import com.scm.model.FeeStructure;

public interface StudentFeeStructureService {
    public List<FeeStructure> getStudentFeeStructure(String studentId);
    public FeeStructure getFeeStructureByYear(int year,String studentId);
}