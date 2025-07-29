package com.scm.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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
    private FeeStructure studentFee;

    private double amountPaid;
    private String paymentMode; // "Online", "Cash", etc.
    private String transactionId;
    private LocalDateTime paymentDate;
}
