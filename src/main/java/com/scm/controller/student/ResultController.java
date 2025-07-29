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
import com.scm.model.StudentResult;
import com.scm.service.Student.StudentResultService;
import com.scm.service.Student.StudentService;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    @Autowired
    private StudentResultService resultService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<List<StudentResult>> allSemesterResult(@RequestHeader(JwtConstant.HEADER) String token){
        String studentId = studentService.getStudentIdByToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(resultService.allSemesterResults(studentId));
    }


    @GetMapping("/result/year/{year}/semester/{semester}")
    public ResponseEntity<StudentResult> semesterResult(@PathVariable int year,@PathVariable int semester,@RequestHeader(JwtConstant.HEADER) String token){
        String studentId = studentService.getStudentIdByToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(resultService.sememsterResult(year,semester,studentId));
    }
}
