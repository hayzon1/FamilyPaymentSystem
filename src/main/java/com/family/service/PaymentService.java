package com.family.service;

import com.family.model.Parent;
import com.family.model.Payment;
import com.family.model.Student;
import com.family.repository.ParentRepository;
import com.family.repository.PaymentRepository;
import com.family.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//@RequiredArgsConstructor
@Service
public class PaymentService {
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(ParentRepository parentRepository,
                          StudentRepository studentRepository,
                          PaymentRepository paymentRepository) {
        this.parentRepository = parentRepository;
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }


    @Transactional
    public void processPayment(Long parentId, Long studentId, Double paymentAmount) {

        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Apply dynamic rate adjustment
        double dynamicRate = 0.05;
        double adjustedAmount = paymentAmount * (1 + dynamicRate);

        // Update student's balance
        double newStudentBalance = student.getBalance() + adjustedAmount;
        student.setBalance(newStudentBalance);
        studentRepository.save(student);

        // Determine if the child is shared or unique
        if (student.getParents().size() == 2) {
            // Shared child: split cost between both parents
            for (Parent p : student.getParents()) {
                double newBalance = p.getBalance() - (adjustedAmount / 2);
                p.setBalance((Double) newBalance);
                parentRepository.save(p);
            }
        } else {
            // Unique child: full cost to the parent
            double newBalance = parent.getBalance() - adjustedAmount;
            parent.setBalance(newBalance);
            parentRepository.save(parent);
        }

        // Record the payment
        Payment payment = new Payment();
        payment.setParentId(parentId);
        payment.setStudentId(studentId);
        payment.setPaymentAmount(paymentAmount);
        payment.setAdjustedAmount(adjustedAmount);
        payment.setTimestamp(LocalDateTime.now());
        paymentRepository.save(payment);
    }
}
