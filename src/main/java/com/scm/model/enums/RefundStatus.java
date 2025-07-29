package com.scm.model.enums;

public enum RefundStatus {
    NOT_REQUESTED, // Default status when no refund is initiated
    REQUESTED,     // Student has requested a refund
    APPROVED,      // Refund has been approved
    REJECTED,      // Refund request was denied
    PROCESSED,     // Refund has been successfully processed and paid
    CANCELLED      // Refund request was cancelled (by student or admin)
}

