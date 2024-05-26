package com.anthonyo.kfc.kfc.entities;

import com.anthonyo.kfc.kfc.enums.ActionStockType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActionStock {
     int id;
     ActionStockType type;
     Instant dateTime;
     Double quantity;
     Ingredient ingredient;
     Stock stock;
}
