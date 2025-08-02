package com.scm.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.model.FeeStructure;
import com.scm.model.Student;
import com.scm.model.enums.RefundStatus;
import com.scm.repository.student.StudentFeeStructureRepository;
import com.scm.service.Student.StudentFeeStructureService;
import com.scm.service.admin.RefundService;

@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private StudentFeeStructureService feeStructureService;

    @Autowired
    private StudentFeeStructureRepository feeStructureRepository;

    @Override
    public String approveRefund(String studentID) {
        List<FeeStructure> studentFeeStructure = feeStructureService.getStudentFeeStructure(studentID);

        FeeStructure currentFees = studentFeeStructure.get(studentFeeStructure.size() - 1);

        double paidAmount = currentFees.getPaidAmount();

        double totalFee = currentFees.getAcademicFee() + currentFees.getExamFee() + currentFees.getOtherFee();

        // Setting due amount just for safety...
        currentFees.setDueAmount(totalFee-paidAmount);
        if(currentFees.getDueAmount() < 0){
            currentFees.setDueAmount(0);
        }

        // updating status...
        if(paidAmount > totalFee){
            currentFees.setRefundStatus(RefundStatus.APPROVED);
            feeStructureRepository.save(currentFees);
            return "Refund Approved";
        }
        currentFees.setRefundStatus(RefundStatus.REJECTED);
        feeStructureRepository.save(currentFees);
        return "Refund Not Approved";
    }

    @Override
    public String refund() {
        List<FeeStructure> ApprovedStudents = feeStructureRepository.findByRefundStatus(RefundStatus.APPROVED);

        for (FeeStructure feeStructure : ApprovedStudents) {
            double paidAmount = feeStructure.getPaidAmount();
            double totalFee = feeStructure.getAcademicFee()+feeStructure.getExamFee()+feeStructure.getOtherFee();

            feeStructure.setRefundAmount(paidAmount-totalFee);
            feeStructure.setRefundStatus(RefundStatus.PROCESSED);

            feeStructureRepository.save(feeStructure);
        }
        return "Refund completed...";
    }
}
