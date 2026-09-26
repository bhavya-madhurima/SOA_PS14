package com.klu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.entity.MenuItem;

public interface MenuItemRepo
        extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByRestaurantId(
            Long restaurantId);
}