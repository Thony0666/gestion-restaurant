package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.RestaurantRequest;
import com.anthonyo.kfc.kfc.dtos.responses.RestaurantResponse;
import com.anthonyo.kfc.kfc.entities.Restaurant;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse create(RestaurantRequest toCreate);
    List<Restaurant> findAll();
}
