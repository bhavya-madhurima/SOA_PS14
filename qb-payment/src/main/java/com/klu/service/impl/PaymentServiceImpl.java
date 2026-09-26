package com.klu.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.klu.entity.Payment;
import com.klu.repo.PaymentRepo;
import com.klu.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;

    public PaymentServiceImpl(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public Payment processPayment(Payment payment) {

        payment.setStatus("SUCCESS");

        payment.setTransactionId(
                "TXN-" + UUID.randomUUID().toString().substring(0, 8)
        );

        return paymentRepo.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {

        return paymentRepo.findAll();
    }

    @Override
    public Payment getPayment(Long id) {

        return paymentRepo.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Payment not found"));
    }

    @Override
    public Payment updatePaymentStatus(Long id, String status) {

        Payment payment = getPayment(id);

        payment.setStatus(status);

        return paymentRepo.save(payment);
    }

    @Override
    public void deletePayment(Long id) {

        paymentRepo.deleteById(id);
    }
}