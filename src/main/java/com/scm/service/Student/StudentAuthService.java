package com.scm.service.Student;

import com.scm.request.LoginRequest;
import com.scm.request.RegisterRequest;

public interface StudentAuthService{
    public String register(RegisterRequest request);
    public String login(LoginRequest request);
}
