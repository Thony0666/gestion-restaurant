package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.RestaurantRequest;
import com.anthonyo.kfc.kfc.dtos.responses.RestaurantResponse;
import com.anthonyo.kfc.kfc.entities.Restaurant;

public interface RestaurantService {
    RestaurantResponse create(RestaurantRequest toCreate);
}
