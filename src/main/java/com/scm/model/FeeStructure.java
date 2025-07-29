package com.scm.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scm.model.enums.FeeStatus;
import com.scm.model.enums.RefundStatus;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    private int year;

    private double academicFee;
    private double examFee;
    private double otherFee;
    private double paidAmount;
    private double dueAmount;
    private double refundAmount;

    @Enumerated(EnumType.STRING)
    private RefundStatus refundStatus;

    @Enumerated(EnumType.STRING)
    private FeeStatus status;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "studentFee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentFeePayment> payments = new ArrayList<>();

    @PreUpdate
    @PrePersist
    public void update() {
        updatedAt = LocalDateTime.now();
        dueAmount = academicFee + examFee + otherFee - paidAmount;
    }
}
