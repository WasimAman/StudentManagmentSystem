package com.scm.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.model.StudentFeePayment;

@Repository
public interface StudentFeePaymentRepository extends JpaRepository<StudentFeePayment,Long>{
    
}
