package com.scm.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.config.jwt.JwtConstant;
import com.scm.service.Student.StudentService;
import com.scm.service.admin.RefundService;

@RestController
@RequestMapping("/api/refund")
public class RefundController {
    @Autowired
    private RefundService refundService;
    @Autowired
    private StudentService studentService;

    @PutMapping("/")
    private ResponseEntity<String> refundAllStudents(){
        String msg = refundService.refund();
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @PutMapping("/approved")
    public ResponseEntity<String> approveRefund(@RequestHeader(JwtConstant.HEADER) String token){
        String studentID = studentService.getStudentIdByToken(token);
        String msg = refundService.approveRefund(studentID);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
