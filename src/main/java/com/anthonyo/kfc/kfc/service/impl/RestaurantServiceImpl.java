package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.RestaurantRequest;
import com.anthonyo.kfc.kfc.dtos.responses.RestaurantResponse;
import com.anthonyo.kfc.kfc.entities.*;
import com.anthonyo.kfc.kfc.exceptions.InternalServerError;
import com.anthonyo.kfc.kfc.mappers.RestaurantMapper;
import com.anthonyo.kfc.kfc.repository.*;
import com.anthonyo.kfc.kfc.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;
    private final StockRepository stockRepository;
    private final MenuRepository menuRepository;
    private final IngredientRepository ingredientRepository;
    private final SaleRepository saleRepository;


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
        var menus = menuRepository.findAll();
        for (var q : menus) {
            var createSale = Sale.builder()
                    .restaurant(Restaurant.builder().id(createdRestaurant.getId()).build())
                    .menu(Menu.builder().id(q.getId()).build())
                    .price(0)
                    .saleDate(Instant.now())
                    .quantity(0)
                    .build();
            saleRepository.creatSale(createSale);
        }
        return restaurantMapper.toResponse(createdRestaurant);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
