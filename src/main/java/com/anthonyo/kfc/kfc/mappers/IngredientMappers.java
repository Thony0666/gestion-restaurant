package com.anthonyo.kfc.kfc.mappers;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientResponse;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Unit;
import org.springframework.stereotype.Component;

@Component
public class IngredientMappers {
    public Ingredient toEntity(IngredientRequest ingredientRequest) {
        return Ingredient
                .builder()
                .id(ingredientRequest.getId())
                .name(ingredientRequest.getName())
                .price(ingredientRequest.getPrice())
                .unit(Unit.builder()
                        .id(ingredientRequest.getUnit())
                        .build())
                .build();
    }
    public IngredientResponse toResponse(Ingredient ingredient) {
        return IngredientResponse.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .price(ingredient.getPrice())
                .build();
    }

}
