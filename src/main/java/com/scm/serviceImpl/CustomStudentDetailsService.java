package com.scm.serviceImpl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.model.Student;
import com.scm.repository.student.StudentRepository;

@Service
public class CustomStudentDetailsService implements UserDetailsService{

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        Optional<Student> opt = studentRepository.findById(studentId);
        if(opt.isEmpty()){
            throw new UsernameNotFoundException("User not found with this studentId : "+studentId);
        }

        Student student = opt.get();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(student.getRole().name());
        UserDetails userDetails = new User(student.getStudentId(),student.getPassword(),Collections.singletonList(authority));
        return userDetails;
    }
    
}
