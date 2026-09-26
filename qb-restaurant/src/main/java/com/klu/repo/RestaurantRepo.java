package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.entity.Restaurant;

public interface RestaurantRepo
        extends JpaRepository<Restaurant, Long> {

}
