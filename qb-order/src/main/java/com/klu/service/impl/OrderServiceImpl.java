package com.klu.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.client.PaymentClient;
import com.klu.dto.PaymentRequest;
import com.klu.entity.Order;
import com.klu.repo.OrderRepo;
import com.klu.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final PaymentClient paymentClient;

    public OrderServiceImpl(
            OrderRepo orderRepo,
            PaymentClient paymentClient) {

        this.orderRepo = orderRepo;
        this.paymentClient = paymentClient;
    }

    @Override
    public Order placeOrder(Order order) {

        order.setStatus("PAYMENT_PENDING");

        Order savedOrder = orderRepo.save(order);

        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setOrderId(savedOrder.getId());
        paymentRequest.setAmount(savedOrder.getTotalAmount());
        paymentRequest.setPaymentMethod("UPI");

        try {

            paymentClient.processPayment(paymentRequest);

            savedOrder.setStatus("PAID");

        } catch (Exception e) {

            savedOrder.setStatus("PAYMENT_FAILED");
        }

        return orderRepo.save(savedOrder);
    }

    @Override
    public List<Order> getAllOrders() {

        return orderRepo.findAll();
    }

    @Override
    public Order getOrder(Long id) {

        return orderRepo.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Order not found"));
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {

        Order order = getOrder(id);

        order.setStatus(status);

        return orderRepo.save(order);
    }

    @Override
    public void deleteOrder(Long id) {

        orderRepo.deleteById(id);
    }
}