package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOfMenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientOfMenuResponse;
import com.anthonyo.kfc.kfc.entities.ActionStock;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;
import com.anthonyo.kfc.kfc.entities.Stock;
import com.anthonyo.kfc.kfc.enums.ActionStockType;
import com.anthonyo.kfc.kfc.exceptions.NotFoundException;
import com.anthonyo.kfc.kfc.mappers.IngredientOfMenuMappers;
import com.anthonyo.kfc.kfc.repository.ActionStockRepository;
import com.anthonyo.kfc.kfc.repository.IngredientOfMenuRepository;
import com.anthonyo.kfc.kfc.repository.StockRepository;
import com.anthonyo.kfc.kfc.service.IngredientOfMenuService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class IngredientOfMenuServiceImpl implements IngredientOfMenuService {
    private final ActionStockRepository actionStockRepository;
    private final StockRepository stockRepository;
    private final IngredientOfMenuRepository ingredientOfMenuRepository;
    private  final IngredientOfMenuMappers ingredientOfMenuMappers;

    public IngredientOfMenuServiceImpl(ActionStockRepository actionStockRepository, StockRepository stockRepository, IngredientOfMenuRepository ingredientOfMenuRepository, IngredientOfMenuMappers ingredientOfMenuMappers) {
        this.actionStockRepository = actionStockRepository;
        this.stockRepository = stockRepository;
        this.ingredientOfMenuRepository = ingredientOfMenuRepository;
        this.ingredientOfMenuMappers = ingredientOfMenuMappers;
    }


    @Override
    public IngredientOfMenuResponse create(IngredientOfMenuRequest toCreate) {
        var stock = stockRepository.findByIngredientId(toCreate.getIngredientId()).orElseThrow(()->new NotFoundException("Not found id"));
        if (stock.getQuantity() < toCreate.getQuantity() || stock.getQuantity() == 0) {
            throw new NotFoundException("Insufficient stock quantity");
        }
        stock.setQuantity(stock.getQuantity() - toCreate.getQuantity());
        stockRepository.update(stock);
        IngredientOfMenu ingredientOfMenu =ingredientOfMenuMappers.toEntity(toCreate);
        var actionStock = ActionStock
                .builder()
                .type(ActionStockType.SORTIE)
                .quantity(toCreate.getQuantity())
                .stock(Stock.builder()
                        .id(stock.getId())
                        .build())
                .ingredient(Ingredient.builder()
                        .id(toCreate.getIngredientId())
                        .build())
                .dateTime(Instant.now())
                .build();
        actionStockRepository.create(actionStock);
        return ingredientOfMenuMappers.toResponse(ingredientOfMenuRepository.create(ingredientOfMenu));
    }
}