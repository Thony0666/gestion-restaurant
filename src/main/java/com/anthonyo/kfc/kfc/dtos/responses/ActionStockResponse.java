package com.anthonyo.kfc.kfc.dtos.responses;

import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Unit;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActionStockResponse {
    Instant actionDate;
    String ingredient;
    String type;
    Double quantity;
    String unit;
}
