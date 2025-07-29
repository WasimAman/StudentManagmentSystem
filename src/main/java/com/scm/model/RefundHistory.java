package com.scm.model;

import com.scm.model.enums.RefundStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private FeeStructure feeStructure;

    @Enumerated(EnumType.STRING)
    private RefundStatus status;
    
    private LocalDateTime changedAt;
}
