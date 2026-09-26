package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}