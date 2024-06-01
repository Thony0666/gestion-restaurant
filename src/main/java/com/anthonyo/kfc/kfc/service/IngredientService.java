package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientResponse;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientWithUnit;
import com.anthonyo.kfc.kfc.entities.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    List<Ingredient> getAllIngredients();
    Optional<IngredientWithUnit> findUnit(Integer ingredientId);
    IngredientResponse create(IngredientRequest toCreate);
    List<IngredientWithUnit> findIngredientWithUnit();
}
