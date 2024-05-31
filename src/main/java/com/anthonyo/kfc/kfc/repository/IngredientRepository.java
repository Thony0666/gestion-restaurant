package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.dtos.responses.IngredientWithUnit;
import com.anthonyo.kfc.kfc.entities.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    Ingredient create(Ingredient toCreate);
    Optional<Ingredient> findById(Integer id);
    List<Ingredient> findAll();
    List<IngredientWithUnit> findIngredientWithUnit();
}
