package com.klu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.klu.entity.MenuItem;
import com.klu.entity.Restaurant;
import com.klu.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(
            RestaurantService restaurantService) {

        this.restaurantService =
                restaurantService;
    }

    @PostMapping
    public Restaurant addRestaurant(
            @RequestBody Restaurant restaurant) {

        return restaurantService
                .addRestaurant(restaurant);
    }

    @GetMapping
    public List<Restaurant>
            getAllRestaurants() {

        return restaurantService
                .getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(
            @PathVariable Long id) {

        return restaurantService
                .getRestaurant(id);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(
            @PathVariable Long id,
            @RequestBody Restaurant restaurant) {

        return restaurantService
                .updateRestaurant(
                        id,
                        restaurant);
    }

    @DeleteMapping("/{id}")
    public String deleteRestaurant(
            @PathVariable Long id) {

        restaurantService
                .deleteRestaurant(id);

        return "Restaurant deleted successfully";
    }

    @PostMapping("/{restaurantId}/menu")
    public MenuItem addMenuItem(
            @PathVariable Long restaurantId,
            @RequestBody MenuItem menuItem) {

        return restaurantService
                .addMenuItem(
                        restaurantId,
                        menuItem);
    }

    @GetMapping("/{restaurantId}/menu")
    public List<MenuItem> getMenuItems(
            @PathVariable Long restaurantId) {

        return restaurantService
                .getMenuItems(
                        restaurantId);
    }

    @PutMapping("/menu/{id}")
    public MenuItem updateMenuItem(
            @PathVariable Long id,
            @RequestBody MenuItem menuItem) {

        return restaurantService
                .updateMenuItem(
                        id,
                        menuItem);
    }

    @DeleteMapping("/menu/{id}")
    public String deleteMenuItem(
            @PathVariable Long id) {

        restaurantService
                .deleteMenuItem(id);

        return "Menu item deleted successfully";
    }
}