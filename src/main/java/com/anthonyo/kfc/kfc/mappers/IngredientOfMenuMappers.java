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
                .menu(Menu.builder()
                        .name(ingredientOfMenuRequest.getMenuName())
                        .price(ingredientOfMenuRequest.getMenuPrice())
                        .build())
                .ingredient(Ingredient.builder()
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
