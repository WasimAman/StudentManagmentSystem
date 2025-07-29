package com.scm.serviceImpl.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.model.FeeStructure;
import com.scm.repository.student.StudentFeeStructureRepository;
import com.scm.service.Student.StudentFeeStructureService;

@Service
public class StudentFeeStructureServiceImpl implements StudentFeeStructureService{
    @Autowired
    private StudentFeeStructureRepository feeStructureRepository;
    @Override
    public List<FeeStructure> getStudentFeeStructure(String studentId) {
        return feeStructureRepository.findAllFeeStructureForStudent(studentId);
    }

    @Override
    public FeeStructure getFeeStructureByYear(int year, String studentId) {
        return feeStructureRepository.findByStudentIdAndYear(studentId,year);
    }
}