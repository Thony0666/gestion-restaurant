package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOfMenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientOfMenuResponse;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;

public interface IngredientOfMenuService {
    IngredientOfMenuResponse create(IngredientOfMenuRequest toCreate);
}
