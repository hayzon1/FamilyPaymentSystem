package com.family.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor

public class PaymentRequest {
    private long parentId;
    private Long studentId;
    private Double paymentAmount;

    public Long getParentId() {
        return parentId;
    }
    public Long getStudentId() {
        return studentId;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }


}
