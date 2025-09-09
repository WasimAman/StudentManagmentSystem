package com.scm.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.config.jwt.JwtConstant;
import com.scm.model.FeeStructure;
import com.scm.service.Student.StudentFeeStructureService;
import com.scm.service.Student.StudentService;

@RestController
@RequestMapping("/api/fee")
public class FeeController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentFeeStructureService feeStructureService;

    @GetMapping("/")
    public ResponseEntity<List<FeeStructure>> studentFeeStrucure(@RequestHeader(JwtConstant.HEADER) String token) {
        String studentId = studentService.getStudentIdByToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(feeStructureService.getStudentFeeStructure(studentId));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<FeeStructure> studentFeeStructurePerYear(
            @RequestHeader(JwtConstant.HEADER) String token,
            @PathVariable int year) {

        System.out.println(year);
        System.out.println(token)
        String studentId = studentService.getStudentIdByToken(token);
        FeeStructure feeStructureByYear = feeStructureService.getFeeStructureByYear(year, studentId);
        return ResponseEntity.status(HttpStatus.OK).body(feeStructureByYear);
    }
}
