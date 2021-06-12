package com.example.project.Controller;

import com.example.project.Entity.Payment;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) throws ResourceNotFoundException {
        return paymentService.getPayment(id);
    }

    @PutMapping("/{id}")
    public Payment updatePaymentById(@PathVariable Long id, @RequestBody Payment payment) throws ResourceNotFoundException {
        return paymentService.updatePaymentById(id, payment);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentById(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) throws ResourceNotFoundException {
        return paymentService.createPayment(payment);
    }
}
