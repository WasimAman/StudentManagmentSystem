package com.scm.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.service.Student.StudentRefundService;
import com.scm.service.Student.StudentService;

@RestController
@RequestMapping("/api/refund")
public class RefundController {

    @Autowired
    private StudentRefundService refundService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/request")
    public ResponseEntity<String> requestRefund(@RequestHeader String token){
        String studentId = studentService.getStudentIdByToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(refundService.requestRefund(studentId));
    }

    @PostMapping("/cancle")
    public ResponseEntity<String> cancleRefund(@RequestHeader String token){
        String studentId = studentService.getStudentIdByToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(refundService.cancleRefund(studentId));
    }

    @GetMapping("/status")
    public ResponseEntity<String> checkRefundStatus(@RequestHeader String token){
        String studentId = studentService.getStudentIdByToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(refundService.checkRefundStatus(studentId));
    }
}
