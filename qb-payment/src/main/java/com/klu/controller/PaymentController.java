package com.klu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.klu.entity.Payment;
import com.klu.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment processPayment(@RequestBody Payment payment) {

        return paymentService.processPayment(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {

        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {

        return paymentService.getPayment(id);
    }

    @PutMapping("/{id}/status")
    public Payment updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return paymentService.updatePaymentStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {

        paymentService.deletePayment(id);

        return "Payment deleted successfully";
    }
}