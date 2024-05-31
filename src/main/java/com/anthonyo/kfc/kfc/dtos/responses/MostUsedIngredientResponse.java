package com.anthonyo.kfc.kfc.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MostUsedIngredientResponse {
    Integer ingredient;
    String ingredientName;
    String menuName;
    Double quantityExpenses;
    String unit;
}
