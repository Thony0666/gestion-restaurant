package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.RestaurantRequest;
import com.anthonyo.kfc.kfc.dtos.responses.RestaurantResponse;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.entities.Stock;
import com.anthonyo.kfc.kfc.exceptions.InternalServerError;
import com.anthonyo.kfc.kfc.mappers.RestaurantMapper;
import com.anthonyo.kfc.kfc.repository.IngredientRepository;
import com.anthonyo.kfc.kfc.repository.RestaurantRepository;
import com.anthonyo.kfc.kfc.repository.StockRepository;
import com.anthonyo.kfc.kfc.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;
    private final StockRepository stockRepository;
    private final IngredientRepository ingredientRepository;


    @Override
    public RestaurantResponse create(RestaurantRequest toCreate) {
        var ingredients = ingredientRepository.findAll();
        var restaurant = restaurantMapper.toEntity(toCreate);

        if (ingredients.isEmpty()){
        return restaurantMapper.toResponse(restaurantRepository.create(restaurant));
        }
        var createdRestaurant = restaurantRepository.create(restaurant);
        for (var ingredient : ingredients){
            var stock = Stock.builder()
                    .ingredient(ingredient)
                    .quantity(0.00)
                    .restaurant(createdRestaurant)
                    .build();
            stockRepository.create(stock);
        }
        return restaurantMapper.toResponse(createdRestaurant);
    }
}
