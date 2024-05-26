package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.StockRequest;
import com.anthonyo.kfc.kfc.dtos.responses.StockResponse;
import com.anthonyo.kfc.kfc.entities.ActionStock;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.enums.ActionStockType;
import com.anthonyo.kfc.kfc.exceptions.NotFoundException;
import com.anthonyo.kfc.kfc.mappers.CreateStockMapper;
import com.anthonyo.kfc.kfc.repository.ActionStockRepository;
import com.anthonyo.kfc.kfc.repository.StockRepository;
import com.anthonyo.kfc.kfc.service.StockService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final CreateStockMapper createStockMapper;
    private final ActionStockRepository actionStockRepository;

    public StockServiceImpl(StockRepository stockRepository, CreateStockMapper createStockMapper, ActionStockRepository actionStockRepository) {
        this.stockRepository = stockRepository;
        this.createStockMapper = createStockMapper;
        this.actionStockRepository = actionStockRepository;
    }


    @Override
    public StockResponse createStock(StockRequest stockRequest) {
        var stock = createStockMapper.toEntity(stockRequest);
        var createdStock = stockRepository.create(stock);
//        var stockByIdIngredient = stockRepository.findByIngredientId(stockRequest.getIngredientId()).orElseThrow(() -> new NotFoundException("Ingredient not found"));
//        var actionStock = ActionStock
//                .builder()
//                .quantity(stockRequest.getQuantity())
//                .ingredient(Ingredient
//                                .builder()
//                                .id(stockRequest.getIngredientId())
//                                .build())
//                .stock(stockByIdIngredient)
//                .dateTime(Instant.now())
//                .type(ActionStockType.ENTRY)
//                .build();
//        actionStockRepository.create(actionStock);
        return createStockMapper.toResponse(createdStock);
    }

    @Override
    public StockResponse updateStock(StockRequest stockRequest) {
        var stock = stockRepository.findByIngredientId(stockRequest.getIngredientId()).orElseThrow(() -> new NotFoundException("Ingredient not found"));
        stock.setQuantity(stock.getQuantity() + stockRequest.getQuantity());
        stockRepository.update(stock);
        var actionStock = ActionStock
                .builder()
                .quantity(stockRequest.getQuantity())
                .type(ActionStockType.ENTRY)
                .ingredient(Ingredient
                        .builder()
                        .id(stockRequest.getIngredientId())
                        .build())
                .stock(stock)
                .dateTime(Instant.now())
                .build();
        actionStockRepository.create(actionStock);
        return createStockMapper.toResponse(stock);
    }


}
