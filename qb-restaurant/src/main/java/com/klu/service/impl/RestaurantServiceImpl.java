package com.klu.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.entity.MenuItem;
import com.klu.entity.Restaurant;
import com.klu.repo.MenuItemRepo;
import com.klu.repo.RestaurantRepo;
import com.klu.service.RestaurantService;

@Service
public class RestaurantServiceImpl
        implements RestaurantService {

    private final RestaurantRepo restaurantRepo;

    private final MenuItemRepo menuItemRepo;

    public RestaurantServiceImpl(
            RestaurantRepo restaurantRepo,
            MenuItemRepo menuItemRepo) {

        this.restaurantRepo = restaurantRepo;
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public Restaurant addRestaurant(
            Restaurant restaurant) {

        return restaurantRepo.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        return restaurantRepo.findAll();
    }

    @Override
    public Restaurant getRestaurant(Long id) {

        return restaurantRepo.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Restaurant not found"));
    }

    @Override
    public Restaurant updateRestaurant(
            Long id,
            Restaurant restaurant) {

        Restaurant existing =
                getRestaurant(id);

        existing.setName(
                restaurant.getName());

        existing.setLocation(
                restaurant.getLocation());

        existing.setPhone(
                restaurant.getPhone());

        existing.setAvailable(
                restaurant.isAvailable());

        return restaurantRepo.save(existing);
    }

    @Override
    public void deleteRestaurant(Long id) {

        restaurantRepo.deleteById(id);
    }

    @Override
    public MenuItem addMenuItem(
            Long restaurantId,
            MenuItem menuItem) {

        getRestaurant(restaurantId);

        menuItem.setRestaurantId(
                restaurantId);

        return menuItemRepo.save(menuItem);
    }

    @Override
    public List<MenuItem> getMenuItems(
            Long restaurantId) {

        getRestaurant(restaurantId);

        return menuItemRepo
                .findByRestaurantId(
                        restaurantId);
    }

    @Override
    public MenuItem updateMenuItem(
            Long id,
            MenuItem menuItem) {

        MenuItem existing =
                menuItemRepo.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Menu item not found"));

        existing.setName(
                menuItem.getName());

        existing.setCategory(
                menuItem.getCategory());

        existing.setPrice(
                menuItem.getPrice());

        existing.setAvailable(
                menuItem.isAvailable());

        return menuItemRepo.save(existing);
    }

    @Override
    public void deleteMenuItem(Long id) {

        menuItemRepo.deleteById(id);
    }
}