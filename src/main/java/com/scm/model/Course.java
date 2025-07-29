package com.scm.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    
    private String name;
    private String branch;
    @Id
    private String branchCode;
    private int duration; // in years
    private double totalFee;
    private double perYearFee;
}
