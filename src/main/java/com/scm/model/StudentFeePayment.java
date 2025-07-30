package com.scm.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentFeePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_fee_id")
    @JsonBackReference
    private FeeStructure studentFee;

    private double amountPaid;
    private String paymentMode; // "Online", "Cash", etc.
    private String transactionId;
    private LocalDateTime paymentDate;
}
