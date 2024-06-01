package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientResponse;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientWithUnit;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Stock;
import com.anthonyo.kfc.kfc.exceptions.InternalServerError;
import com.anthonyo.kfc.kfc.exceptions.NotFoundException;
import com.anthonyo.kfc.kfc.mappers.IngredientMappers;
import com.anthonyo.kfc.kfc.repository.IngredientRepository;
import com.anthonyo.kfc.kfc.repository.RestaurantRepository;
import com.anthonyo.kfc.kfc.repository.StockRepository;
import com.anthonyo.kfc.kfc.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMappers ingredientMappers;
    private final StockRepository stockRepository;
    private final RestaurantRepository restaurantRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMappers ingredientMappers, StockRepository stockRepository, RestaurantRepository restaurantRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMappers = ingredientMappers;
        this.stockRepository = stockRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<IngredientWithUnit> findUnit(Integer ingredientId) {
        return ingredientRepository.findUnit(ingredientId);
    }

    @Override
    public IngredientResponse create(IngredientRequest toCreate) {
        var restaurants = restaurantRepository.findAll();
        var ingredient = ingredientMappers.toEntity(toCreate);
        if (restaurants.isEmpty()){
            throw new NotFoundException("Restaurant not found");
        }
        var createdIngredient = ingredientRepository.create(ingredient);
        for (var restaurant : restaurants){
            var stock = Stock.builder()
                    .restaurant(restaurant)
                    .quantity(0.00)
                    .ingredient(createdIngredient)
                    .build();
            stockRepository.create(stock);
        }
        return ingredientMappers.toResponse(createdIngredient);
    }

    @Override
    public List<IngredientWithUnit> findIngredientWithUnit() {
        return ingredientRepository.findIngredientWithUnit();
    }
}
