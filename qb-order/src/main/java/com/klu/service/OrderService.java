package com.klu.service;

import java.util.List;

import com.klu.entity.Order;

public interface OrderService {

    Order placeOrder(Order order);

    List<Order> getAllOrders();

    Order getOrder(Long id);

    Order updateOrderStatus(Long id, String status);

    void deleteOrder(Long id);
}