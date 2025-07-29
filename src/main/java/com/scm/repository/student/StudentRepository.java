package com.scm.repository.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    Optional<Student> findByEmail(String email);
}
