package com.scm.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.config.jwt.JwtConstant;
import com.scm.model.Student;
import com.scm.service.Student.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/profile")
    public ResponseEntity<Student> profile(@RequestHeader(JwtConstant.HEADER) String token){
        System.out.println("***************************");
        System.out.println(token);
        System.out.println("***************************");
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentProfileByToken(token));
    }
}

