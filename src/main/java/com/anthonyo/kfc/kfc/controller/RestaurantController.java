package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.RestaurantRequest;
import com.anthonyo.kfc.kfc.dtos.responses.RestaurantResponse;
import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("create")
    public RestaurantResponse restaurantResponse(@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.create(restaurantRequest);
    }
}
