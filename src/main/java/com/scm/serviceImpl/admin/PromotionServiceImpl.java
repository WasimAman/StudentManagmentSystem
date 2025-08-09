package com.scm.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.model.Student;
import com.scm.repository.student.StudentRepository;
import com.scm.service.admin.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public String promoteStudents(int year) {
        List<Student> students = studentRepository.findByCurrentYear(year);
        for (Student student : students) {
            student.setCurrentYear(year+1);
            student.setCurrentSemester(1);

            studentRepository.save(student);
        }
        return "All "+year+" students promoted to next year....";
    }
    
}
