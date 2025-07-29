package com.scm.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.request.LoginRequest;
import com.scm.request.RegisterRequest;
import com.scm.response.AuthResponse;
import com.scm.service.Student.StudentAuthService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    
    @Autowired    
    private StudentAuthService studentService;


    @PostMapping("register")
    public ResponseEntity<AuthResponse> registerHandler(@RequestBody RegisterRequest request){
        System.out.println(request);
        AuthResponse response = new AuthResponse();
        response.setToken(studentService.register(request));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("signin")
    public ResponseEntity<String> loginHandler(@RequestBody LoginRequest request){
        
        String token = studentService.login(request);
        System.out.println("**************************************");
        System.out.println(request.getStudentId());
        System.out.println(request.getPassword());
        System.out.println(token);
        System.out.println("**************************************");
        System.out.println("Login.........");
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}