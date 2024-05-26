package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.entities.Stock;

import java.util.Optional;

public interface StockRepository {
    Stock create(Stock toCreate);
    Stock update(Stock toUpdate);
    Optional <Stock> findByIngredientId(Integer id);
}
