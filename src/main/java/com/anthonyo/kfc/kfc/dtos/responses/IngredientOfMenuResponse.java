package com.anthonyo.kfc.kfc.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientOfMenuResponse {
    Integer id;
    Double quantity;
    Integer menuId;
    Integer ingredientId;
}
