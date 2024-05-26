package com.anthonyo.kfc.kfc.dtos.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientOfMenuRequest {
    Integer id;
    Double quantity;
    Integer menuId;
    Integer ingredientId;
}
