package com.klu.service;

import java.util.List;

import com.klu.entity.MenuItem;
import com.klu.entity.Restaurant;

public interface RestaurantService {

    Restaurant addRestaurant(
            Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurant(Long id);

    Restaurant updateRestaurant(
            Long id,
            Restaurant restaurant);

    void deleteRestaurant(Long id);

    MenuItem addMenuItem(
            Long restaurantId,
            MenuItem menuItem);

    List<MenuItem> getMenuItems(
            Long restaurantId);

    MenuItem updateMenuItem(
            Long id,
            MenuItem menuItem);

    void deleteMenuItem(Long id);
}