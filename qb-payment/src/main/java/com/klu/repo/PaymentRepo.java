package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

}