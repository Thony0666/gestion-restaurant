package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientResponse;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.exceptions.InternalServerError;
import com.anthonyo.kfc.kfc.mappers.IngredientMappers;
import com.anthonyo.kfc.kfc.repository.IngredientRepository;
import com.anthonyo.kfc.kfc.service.IngredientService;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMappers ingredientMappers;

    public IngredientServiceImpl( IngredientRepository ingredientRepository, IngredientMappers ingredientMappers) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMappers = ingredientMappers;
    }

    @Override
    public IngredientResponse create(IngredientRequest toCreate) {
        Ingredient ingredient = ingredientMappers.toEntity(toCreate);
        if (ingredient.getId() != null){
            throw new InternalServerError("Ingredient already exists");
        }
        return ingredientMappers.toResponse(ingredientRepository.create(ingredient));
    }
}
