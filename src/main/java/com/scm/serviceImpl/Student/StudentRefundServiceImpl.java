package com.scm.serviceImpl.Student;

import org.springframework.stereotype.Service;

import com.scm.model.FeeStructure;
import com.scm.model.Student;
import com.scm.model.enums.RefundStatus;
import com.scm.repository.student.StudentFeeStructureRepository;
import com.scm.service.Student.StudentFeeStructureService;
import com.scm.service.Student.StudentRefundService;
import com.scm.service.Student.StudentService;

@Service
public class StudentRefundServiceImpl implements StudentRefundService{
    private StudentFeeStructureRepository feeStructureRepository;
    private StudentFeeStructureService feeStructureService;
    private StudentService studentService;
    @Override
    public String requestRefund(String studentId) {
        Student student = studentService.getStudentProfileById(studentId);
        FeeStructure currentYearFeeStructure = feeStructureService.getFeeStructureByYear(student.getCurrentYear(), studentId);
        currentYearFeeStructure.setRefundStatus(RefundStatus.REQUESTED);
        feeStructureRepository.save(currentYearFeeStructure);
        return "Refund Requested Successfullly...";
    }

    @Override
    public String checkRefundStatus(String studentId) {
        Student student = studentService.getStudentProfileById(studentId);
        FeeStructure currentYearFeeStructure = feeStructureService.getFeeStructureByYear(student.getCurrentYear(), studentId);
        return currentYearFeeStructure.getRefundStatus().name();
    }

    @Override
    public String cancleRefund(String studentId) {
        Student student = studentService.getStudentProfileById(studentId);
        FeeStructure currentYearFeeStructure = feeStructureService.getFeeStructureByYear(student.getCurrentYear(), studentId);
        currentYearFeeStructure.setRefundStatus(RefundStatus.CANCELLED);
        feeStructureRepository.save(currentYearFeeStructure);
        return "Refund Cancled Successfullly...";
    }
}
