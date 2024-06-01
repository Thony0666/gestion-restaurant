package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.ActionStockDateRangeRequest;
import com.anthonyo.kfc.kfc.dtos.requests.StockRequest;
import com.anthonyo.kfc.kfc.dtos.requests.SupplyStockRequest;
import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.dtos.responses.StockResponse;
import com.anthonyo.kfc.kfc.entities.ActionStock;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.entities.Stock;
import com.anthonyo.kfc.kfc.enums.ActionStockType;
import com.anthonyo.kfc.kfc.exceptions.NotFoundException;
import com.anthonyo.kfc.kfc.mappers.CreateStockMapper;
import com.anthonyo.kfc.kfc.repository.ActionStockRepository;
import com.anthonyo.kfc.kfc.repository.StockRepository;
import com.anthonyo.kfc.kfc.service.StockService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        return createStockMapper.toResponse(createdStock);
    }

    @Override
    public StockResponse updateStock(SupplyStockRequest supplyStockRequest) {
        var stockById =stockRepository.findByIngredientId(supplyStockRequest).orElseThrow(()->new NotFoundException("stock not found"));
        var stock = Stock.builder()
                .restaurant(Restaurant.builder()
                        .id(supplyStockRequest.getRestaurant())
                        .build())
                .ingredient(Ingredient.builder()
                        .id(supplyStockRequest.getIngredient())
                        .build())
                .quantity(stockById.getQuantity() + supplyStockRequest.getNewQuantity())
                .build();
               stockRepository.update(stock);
        var action = ActionStock.builder()
                .ingredient(Ingredient.builder()
                        .id(supplyStockRequest.getIngredient())
                        .build())
                .type(ActionStockType.ENTRY)
                .dateTime(Instant.now())
                .stock(Stock.builder()
                        .id(stockById.getId())
                        .build())
                .quantity(supplyStockRequest.getNewQuantity())
                .build();
        var actionStock = actionStockRepository.create(action);
        return StockResponse.builder()
                .quantity(actionStock.getQuantity())
                .id(actionStock.getId())
                .build();
    }

    @Override
    public List<ActionStockResponse> findByBetweenDate(String startDate, String endDate ,Integer restaurantId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime parsedDateStart = LocalDateTime.parse(startDate, formatter);
        LocalDateTime parsedDateEnd = LocalDateTime.parse(endDate, formatter);
        Instant startInstant = parsedDateStart.atZone(ZoneId.systemDefault()).toInstant();
        Instant endInstant = parsedDateEnd.atZone(ZoneId.systemDefault()).toInstant();
        return stockRepository.findByBetweenDate(restaurantId, startInstant, endInstant );
    }
}
