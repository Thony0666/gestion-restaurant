package com.anthonyo.kfc.kfc.mappers;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOfMenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientOfMenuResponse;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;
import com.anthonyo.kfc.kfc.entities.Menu;
import org.springframework.stereotype.Component;

@Component
public class IngredientOfMenuMappers {
    public IngredientOfMenu toEntity(IngredientOfMenuRequest ingredientOfMenuRequest) {
        return IngredientOfMenu.builder()
                .id(ingredientOfMenuRequest.getId())
                .quantity(ingredientOfMenuRequest.getQuantity())
                .menu(Menu.builder()
                        .id(ingredientOfMenuRequest.getMenuId())
                        .build())
                .ingredient(Ingredient.builder()
                        .id(ingredientOfMenuRequest.getIngredientId())
                        .build())
                .build();
    }

    public IngredientOfMenuResponse toResponse(IngredientOfMenu ingredientOfMenu) {
        return IngredientOfMenuResponse.builder()
                .id(ingredientOfMenu.getId())
                .quantity(ingredientOfMenu.getQuantity())
                .menuId(ingredientOfMenu.getMenu().getId())
                .ingredientId(ingredientOfMenu.getIngredient().getId())
                .build();
    }
}
