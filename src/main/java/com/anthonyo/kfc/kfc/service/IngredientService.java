package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientResponse;
import com.anthonyo.kfc.kfc.entities.Ingredient;

public interface IngredientService {
    IngredientResponse create(IngredientRequest toCreate);
}
