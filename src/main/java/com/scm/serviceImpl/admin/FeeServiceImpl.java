package com.scm.serviceImpl.admin;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.model.FeeStructure;
import com.scm.model.StudentFeePayment;
import com.scm.model.enums.FeeStatus;
import com.scm.repository.student.StudentFeeStructureRepository;
import com.scm.request.PaymentRequest;
import com.scm.service.Student.StudentFeeStructureService;
import com.scm.service.admin.FeeService;

@Service
public class FeeServiceImpl implements FeeService{
    @Autowired
    private StudentFeeStructureService feeStructureService;
    @Autowired
    private StudentFeeStructureRepository feeStructureRepository;
    public String payFee(String studentID, PaymentRequest paymentRequest) {
        StudentFeePayment feePayment = new StudentFeePayment();
        feePayment.setAmountPaid(paymentRequest.getAmount());
        feePayment.setPaymentMode(paymentRequest.getPaymentMode());
        feePayment.setTransactionId(paymentRequest.getTransectionID());
        feePayment.setPaymentDate(LocalDateTime.now());
        

        List<FeeStructure> studentFeeStructures = feeStructureService.getStudentFeeStructure(studentID);

        FeeStructure feeStructure = studentFeeStructures.get(studentFeeStructures.size()-1);
        feeStructure.setPaidAmount(feeStructure.getPaidAmount()+feePayment.getAmountPaid());

        feeStructure.setDueAmount(feeStructure.getDueAmount()-feePayment.getAmountPaid());

        if(feeStructure.getDueAmount() < 0){
            feeStructure.setDueAmount(0);
        }

        feeStructure.setStatus(FeeStatus.PARTIAL);
        feeStructure.getPayments().add(feePayment);
        feePayment.setStudentFee(feeStructure);

        feeStructureRepository.save(feeStructure);
        // feePaymentRepository.save(feePayment);
        return "Fee Added...";
    }
    
}
