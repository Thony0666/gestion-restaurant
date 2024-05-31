package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.RestaurantRequest;
import com.anthonyo.kfc.kfc.dtos.responses.RestaurantResponse;

public interface RestaurantService {
    RestaurantResponse create(RestaurantRequest toCreate);
}
