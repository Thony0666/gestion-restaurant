package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOfMenuRequest;
import com.anthonyo.kfc.kfc.dtos.requests.UpdateQteIOFRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IOMResponse;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientOfMenuResponse;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;

import java.util.List;

public interface IngredientOfMenuService {
    void create(IngredientOfMenuRequest toCreate);
    void updateQte(UpdateQteIOFRequest updateQteIOFRequest);
    List<IOMResponse> getByMenuId(Long menuId);
}
