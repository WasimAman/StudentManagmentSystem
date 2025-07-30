package com.scm.service.admin;

import com.scm.request.PaymentRequest;

public interface FeeService {
    //  For admin
    public String payFee(String studentID,PaymentRequest paymentRequest);
}
