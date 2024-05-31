package com.anthonyo.kfc.kfc.mappers;

import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.entities.ActionStock;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActionStockMappers {
    public ActionStockResponse toActionStockResponse(ActionStock actionStock) {
        return ActionStockResponse.builder()
                .actionDate(LocalDateTime.from(actionStock.getDateTime()))
                .type(actionStock.getType().name())
                .ingredient(actionStock.getIngredient().getName())
                .unit(actionStock.getIngredient().getUnit().getName())
                .build();
    }
}
