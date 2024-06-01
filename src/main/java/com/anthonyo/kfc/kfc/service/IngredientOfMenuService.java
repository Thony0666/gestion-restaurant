package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOfMenuRequest;
import com.anthonyo.kfc.kfc.dtos.requests.IngredientOneOfMenu;
import com.anthonyo.kfc.kfc.dtos.requests.UpdateQteIOFRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IOMResponse;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientOfMenuResponse;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;

import java.util.List;
import java.util.Optional;

public interface IngredientOfMenuService {
    void create(IngredientOfMenuRequest toCreate);
    public IngredientOneOfMenu createOneToOne(IngredientOneOfMenu toCreate);
    void updateQte(UpdateQteIOFRequest updateQteIOFRequest);
    List<IOMResponse> getByMenuId(Long menuId);
    Optional<IOMResponse> deleteByManyId(Integer menuId, Integer ingredientId);
}
