package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.dtos.requests.SupplyStockRequest;
import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.dtos.responses.StockResponse;
import com.anthonyo.kfc.kfc.entities.Stock;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface StockRepository {
    Stock create(Stock toCreate);
    StockResponse update(Stock toUpdate);
    Optional <Stock> findByIngredientId(SupplyStockRequest supplyStockRequest);
    Optional <Stock> findByIngredientIdSale(Integer ingredientId,Integer restaurantId);
    List<ActionStockResponse> findByBetweenDate(Integer restaurantId, Instant startInstant, Instant endInstant);
}
