package com.example.project.Service;

import com.example.project.Entity.Payment;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPayment(Long id) throws ResourceNotFoundException;
    Payment createPayment(Payment payment) throws ResourceNotFoundException;
    void deletePaymentById(Long id);
    Payment updatePaymentById(Long id, Payment payment) throws ResourceNotFoundException;
}
