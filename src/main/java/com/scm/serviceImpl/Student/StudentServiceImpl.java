package com.scm.serviceImpl.Student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.config.jwt.JwtProvider;
import com.scm.model.Student;
import com.scm.repository.student.StudentRepository;
import com.scm.service.Student.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student getStudentProfileByToken(String token) {
        String studentId = jwtProvider.getStudentIdFromToken(token);
        return getStudentProfileById(studentId);
    }
    @Override
    public Student getStudentProfileById(String studentId) {
        Optional<Student> opt = studentRepository.findById(studentId);
        return opt.get();
    }

    @Override
    public String getStudentIdByToken(String token){
        return jwtProvider.getStudentIdFromToken(token);
    }
    
}
