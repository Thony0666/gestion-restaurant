package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.entities.Restaurant;

public interface RestaurantRepository {
    Restaurant create(Restaurant toCreate);
}
