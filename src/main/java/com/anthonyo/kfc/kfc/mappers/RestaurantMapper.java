package com.anthonyo.kfc.kfc.mappers;

import com.anthonyo.kfc.kfc.dtos.requests.RestaurantRequest;
import com.anthonyo.kfc.kfc.dtos.responses.RestaurantResponse;
import com.anthonyo.kfc.kfc.entities.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public Restaurant toEntity(RestaurantRequest restaurantRequest){
        return Restaurant.builder()
                .id(restaurantRequest.getId())
                .place(restaurantRequest.getPlace())
                .build();
    }
    public RestaurantResponse toResponse(Restaurant restaurant){
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .place(restaurant.getPlace())
                .build();
    }
}
