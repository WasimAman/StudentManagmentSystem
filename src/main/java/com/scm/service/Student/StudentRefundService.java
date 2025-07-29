package com.scm.service.Student;

public interface StudentRefundService {
    public String requestRefund(String studentId);
    public String checkRefundStatus(String studentId);
    public String cancleRefund(String studentId);
}
