package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.dtos.requests.UpdateQteIOFRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IOMResponse;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;

import java.util.List;
import java.util.Optional;

public interface IngredientOfMenuRepository {
    IngredientOfMenu create(IngredientOfMenu toCreate);
    Optional<IngredientOfMenu> findByManyID(Integer menuId,Integer ingredientId);
    void update(UpdateQteIOFRequest updateQteIOFRequest);
    List<IngredientOfMenu> findByIdMenu(Integer menuId);
    List<IOMResponse> findAllByMenuId(Long menuId);
}
