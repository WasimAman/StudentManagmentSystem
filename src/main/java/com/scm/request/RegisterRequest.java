package com.scm.request;

import java.time.LocalDate;
import com.scm.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String studentPhone;
    private String parentsPhone;
    private LocalDate dateOfBirth;
    private String gender;
    private String fatherName;
    private String motherName;
    private String courseName;
    private String branchName;
    private String branchCode;
    private Address address;
}
