package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.RestaurantRequest;
import com.anthonyo.kfc.kfc.dtos.responses.RestaurantResponse;
import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.exceptions.InternalServerError;
import com.anthonyo.kfc.kfc.mappers.RestaurantMapper;
import com.anthonyo.kfc.kfc.repository.RestaurantRepository;
import com.anthonyo.kfc.kfc.service.RestaurantService;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantMapper restaurantMapper, RestaurantRepository restaurantRepository) {
        this.restaurantMapper = restaurantMapper;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantResponse create(RestaurantRequest toCreate) {
        Restaurant restaurant = restaurantMapper.toEntity(toCreate);

        if (toCreate.getId() != null){
            throw new InternalServerError("Restaurant with id " + toCreate.getId() + " already exists");
        }
        return restaurantMapper.toResponse(restaurantRepository.create(restaurant));
    }
}
