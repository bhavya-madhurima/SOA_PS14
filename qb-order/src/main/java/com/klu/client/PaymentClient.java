package com.klu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.klu.dto.PaymentRequest;

@FeignClient(name = "qb-payment")
public interface PaymentClient {

    @PostMapping("/payments")
    Object processPayment(@RequestBody PaymentRequest paymentRequest);
}
