package com.family.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    @Column(name = "student_id", nullable = false)
    private Long studentId;
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Column(name = "payment_amount", nullable = false)
    private Double paymentAmount;
    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Column(name = "adjusted_amount", nullable = false)
    private Double adjustedAmount;
    public void setAdjustedAmount(Double adjustedAmount) {
        this.adjustedAmount = adjustedAmount;
    }

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


}
