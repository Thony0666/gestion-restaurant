package com.anthonyo.kfc.kfc.mappers;

import com.anthonyo.kfc.kfc.dtos.requests.StockRequest;
import com.anthonyo.kfc.kfc.dtos.responses.StockResponse;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Stock;
import org.springframework.stereotype.Component;

@Component
public class CreateStockMapper {
    public Stock toEntity( StockRequest stock) {
        return Stock.builder()
                .quantity( stock.getQuantity() )
                .ingredient(Ingredient.builder()
                        .id(stock.getIngredientId())
                        .build())
                .build();
    }

    public StockResponse toResponse( Stock stock) {
        return StockResponse.builder()
                .quantity(stock.getQuantity())
                .id(stock.getId())
                .build();
    }
}
