package com.family.controller;

import com.family.dto.PaymentRequest;
import com.family.service.PaymentService;
import lombok.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@RequiredArgsConstructor

public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequest Request) {
        paymentService.processPayment(
             Request.getParentId(),
                Request.getStudentId(),
              Request.getPaymentAmount()
        );
        return ResponseEntity.ok("Payment made");
    }
}
