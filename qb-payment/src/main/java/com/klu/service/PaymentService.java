package com.klu.service;

import java.util.List;

import com.klu.entity.Payment;

public interface PaymentService {

    Payment processPayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPayment(Long id);

    Payment updatePaymentStatus(Long id, String status);

    void deletePayment(Long id);
}