package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.entities.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant create(Restaurant toCreate);
    List<Restaurant> findAll();
}
