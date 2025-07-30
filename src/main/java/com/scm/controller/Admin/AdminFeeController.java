package com.scm.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.config.jwt.JwtConstant;
import com.scm.request.PaymentRequest;
import com.scm.service.Student.StudentService;
import com.scm.service.admin.FeeService;

@RestController
@RequestMapping("/api/fee")
public class AdminFeeController {
    
    @Autowired
    private FeeService feeService;
    @Autowired
    private StudentService studentService;
    @PostMapping("/pay")
    public ResponseEntity<String> payFee(@RequestHeader(JwtConstant.HEADER) String token,@RequestBody PaymentRequest paymentRequest){
        System.out.println("________________________________");
        System.out.println(paymentRequest);
        System.out.println("________________________________");
        String studentID = studentService.getStudentIdByToken(token);
        String msg = feeService.payFee(studentID, paymentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
